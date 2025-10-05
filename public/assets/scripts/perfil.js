document.addEventListener('DOMContentLoaded', () => {
    const API_URL = 'https://6862d10a96f0cc4e34bb10c3.mockapi.io/api/v1/users';

    // --- NUEVO: claves y refs para avatar ---
    const AVATAR_STORAGE_KEY = 'profileAvatarDataUrl';
    const avatarPreview = document.getElementById('avatar-preview'); // <div class="avatar-preview">
    const avatarInitials = document.getElementById('avatar-initials'); // <span id="avatar-initials">--</span>
    const imageInput = document.getElementById('image-upload'); // <input id="image-upload" type="file" accept="image/png, image/jpeg">

    let initialProfileState = {};
    let currentUser = null;

    const profileForm = document.getElementById('profile-form');
    const passwordForm = document.getElementById('password-form');
    const saveProfileBtn = document.getElementById('save-profile-btn');

    // ------------------ Utilidades Avatar ------------------
    const applyAvatar = (dataUrl) => {
        if (!avatarPreview) return;
        if (dataUrl) {
            avatarPreview.style.backgroundImage = `url('${dataUrl}')`;
            avatarPreview.style.backgroundSize = 'cover';
            avatarPreview.style.backgroundPosition = 'center';
            if (avatarInitials) avatarInitials.style.display = 'none';
        } else {
            // quitar imagen -> mostrar iniciales
            avatarPreview.style.backgroundImage = '';
            if (avatarInitials) avatarInitials.style.display = '';
        }
    };

    const loadAvatarFromStorage = () => {
        try {
            const dataUrl = localStorage.getItem(AVATAR_STORAGE_KEY);
            if (dataUrl) applyAvatar(dataUrl);
        } catch { /* ignore */ }
    };

    const saveAvatarToStorage = (dataUrl) => {
        try {
            if (dataUrl) localStorage.setItem(AVATAR_STORAGE_KEY, dataUrl);
        } catch { /* espacio insuficiente, etc. */ }
    };

    const handleImageSelected = (file) => {
        if (!file) return;

        // Validaciones básicas
        const allowed = ['image/png', 'image/jpeg'];
        if (!allowed.includes(file.type)) {
            showFeedback('profile-feedback', 'Solo se permiten imágenes PNG o JPG.', 'error');
            return;
        }
        const MAX_MB = 2;
        if (file.size > MAX_MB * 1024 * 1024) {
            showFeedback('profile-feedback', `La imagen supera ${MAX_MB} MB.`, 'error');
            return;
        }

        const reader = new FileReader();
        reader.onload = (e) => {
            const dataUrl = e.target.result;
            applyAvatar(dataUrl);
            saveAvatarToStorage(dataUrl);
            showFeedback('profile-feedback', 'Foto de perfil actualizada.', 'success');
        };
        reader.onerror = () => showFeedback('profile-feedback', 'No se pudo leer la imagen.', 'error');
        reader.readAsDataURL(file);
    };

    // ------------------ Perfil (original) ------------------
    const initProfilePage = () => {
        currentUser = getUserDataFromSession();
        if (!currentUser) return;

        populateProfileData(currentUser);
        loadAvatarFromStorage();                 // <--- NUEVO: cargar foto guardada
        setupEventListeners();
    };

    const getUserDataFromSession = () => {
        const userDataJSON = sessionStorage.getItem('currentUser');
        if (!userDataJSON) {
            window.location.href = 'login.html';
            return null;
        }
        return JSON.parse(userDataJSON);
    };

    const getInitials = (name) => {
        if (!name) return '--';
        const parts = name.trim().split(' ');
        const initials = parts[0][0] + (parts.length > 1 ? parts[parts.length - 1][0] : '');
        return initials.toUpperCase();
    };

    const populateProfileData = (user) => {
        document.getElementById('profile-name-header').textContent = user.name;
        document.getElementById('profile-email-header').textContent = user.email;
        document.getElementById('fullName').value = user.name;
        document.getElementById('email').value = user.email;

        if (avatarInitials) avatarInitials.textContent = getInitials(user.name);
        initialProfileState = { name: user.name, email: user.email };
    };

    const showFeedback = (elementId, message, type) => {
        const feedbackElement = document.getElementById(elementId);
        if (feedbackElement) {
            feedbackElement.textContent = message;
            feedbackElement.className = `feedback-message ${type}`;
            setTimeout(() => {
                feedbackElement.textContent = '';
                feedbackElement.className = 'feedback-message';
            }, 3500);
        }
    };

    const setupEventListeners = () => {
        // Tabs
        const tabs = document.querySelectorAll('.tab-link');
        const sections = document.querySelectorAll('.profile-section');
        tabs.forEach(tab => {
            tab.addEventListener('click', () => {
                tabs.forEach(item => item.classList.remove('active'));
                sections.forEach(section => section.classList.remove('active'));
                tab.classList.add('active');
                document.getElementById(tab.dataset.section).classList.add('active');
            });
        });

        // Mostrar/ocultar contraseña
        document.querySelectorAll('.password-toggle').forEach(toggle => {
            toggle.addEventListener('click', () => {
                const input = toggle.previousElementSibling;
                input.type = input.type === 'password' ? 'text' : 'password';
                toggle.classList.toggle('fa-eye-slash');
            });
        });

        // Habilitar botón Guardar si hay cambios
        profileForm.addEventListener('input', () => {
            const currentState = {
                name: document.getElementById('fullName').value,
                email: document.getElementById('email').value,
            };
            const hasChanged = JSON.stringify(currentState) !== JSON.stringify(initialProfileState);
            saveProfileBtn.disabled = !hasChanged;
        });

        // Guardar cambios de nombre/correo
        profileForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            saveProfileBtn.disabled = true;
            saveProfileBtn.textContent = 'Guardando...';

            const newName = document.getElementById('fullName').value;
            const newEmail = document.getElementById('email').value;

            try {
                const response = await fetch(`${API_URL}?email=${encodeURIComponent(currentUser.email)}`);
                if (!response.ok) throw new Error('Error al buscar el usuario.');
                const users = await response.json();
                if (users.length === 0) throw new Error('Usuario no encontrado en la API.');

                const userId = users[0].id;
                const updateResponse = await fetch(`${API_URL}/${userId}`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ name: newName, email: newEmail }),
                });
                if (!updateResponse.ok) throw new Error('No se pudo actualizar el perfil.');

                const updatedUserData = { name: newName, email: newEmail };
                sessionStorage.setItem('currentUser', JSON.stringify(updatedUserData));
                currentUser = updatedUserData;
                populateProfileData(currentUser);

                showFeedback('profile-feedback', '¡Perfil actualizado con éxito!', 'success');
            } catch (error) {
                console.error("Error al guardar perfil:", error);
                showFeedback('profile-feedback', error.message, 'error');
            } finally {
                saveProfileBtn.textContent = 'Guardar Cambios';
            }
        });

        // Actualizar contraseña
        passwordForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            const updateButton = passwordForm.querySelector('button');
            updateButton.disabled = true;
            updateButton.textContent = 'Actualizando...';

            const currentPassword = document.getElementById('currentPassword').value;
            const newPassword = document.getElementById('newPassword').value;
            const confirmPassword = document.getElementById('confirmPassword').value;

            if (newPassword !== confirmPassword) {
                showFeedback('password-feedback', 'Las nuevas contraseñas no coinciden.', 'error');
                updateButton.disabled = false;
                updateButton.textContent = 'Actualizar Contraseña';
                return;
            }
            if (newPassword.length < 8) {
                showFeedback('password-feedback', 'La nueva contraseña debe tener al menos 8 caracteres.', 'error');
                updateButton.disabled = false;
                updateButton.textContent = 'Actualizar Contraseña';
                return;
            }

            try {
                const response = await fetch(`${API_URL}?email=${encodeURIComponent(currentUser.email)}`);
                if (!response.ok) throw new Error('Error al buscar el usuario.');
                const users = await response.json();
                if (users.length === 0) throw new Error('Usuario no encontrado.');
                const userFromApi = users[0];

                if (userFromApi.password !== currentPassword) {
                    throw new Error('La contraseña actual es incorrecta.');
                }

                const updateResponse = await fetch(`${API_URL}/${userFromApi.id}`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ password: newPassword }),
                });
                if (!updateResponse.ok) throw new Error('No se pudo actualizar la contraseña.');

                showFeedback('password-feedback', '¡Contraseña actualizada con éxito!', 'success');
                passwordForm.reset();
            } catch (error) {
                console.error("Error al cambiar contraseña:", error);
                showFeedback('password-feedback', error.message, 'error');
            } finally {
                updateButton.disabled = false;
                updateButton.textContent = 'Actualizar Contraseña';
            }
        });

        // --- NUEVO: cambio de imagen ---
        if (imageInput) {
            imageInput.addEventListener('change', () => {
                const file = imageInput.files && imageInput.files[0];
                handleImageSelected(file);
            });
        }
    };

    // Init
    initProfilePage();
});
