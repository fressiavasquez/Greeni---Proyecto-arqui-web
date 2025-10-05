// ====== CALENDARIO Greeni: crear, editar y eliminar recordatorios ======
document.addEventListener('DOMContentLoaded', () => {

  // --- ELEMENTOS DEL DOM (IDs existentes en tu calendar.html) ---
  const addEventBtn     = document.getElementById('add-event-btn');
  const calendarEl      = document.getElementById('calendar-container');
  const selectedDayLbl  = document.getElementById('selected-day-label');
  const dayEventsList   = document.getElementById('day-events-list');

  // Modal + formulario
  const eventModal      = document.getElementById('event-modal');
  const eventForm       = document.getElementById('event-form');
  const titleInput      = document.getElementById('event-title');
  const dateInput       = document.getElementById('event-date');
  const typeSelect      = document.getElementById('event-type');
  const cancelBtn       = document.getElementById('cancel-event-btn');

  // Si en tu HTML tienes un botón [x] para cerrar, dale id="close-event-btn"
  const closeBtn        = document.getElementById('close-event-btn');

  // --- Estado de edición ---
  let editingEvent = null; // null => creando; no null => editando

  // --- Mapeos tipo -> clase CSS para FullCalendar y panel lateral ---
  const TYPE_TO_CLASS = {
    riego: 'event-riego',
    poda: 'event-poda',
    fertilizacion: 'event-fertilizacion',
    otro: 'event-otro'
  };

  const CLASS_TO_ICON = {
    'event-riego': 'fa-tint',
    'event-poda': 'fa-cut',
    'event-fertilizacion': 'fa-leaf',
    'event-otro': 'fa-check'
  };

  // --- Helpers de modal ---
  function openModalForCreate(date) {
    editingEvent = null;
    titleInput.value = '';
    dateInput.value  = date ? toDateInputValue(date) : '';
    typeSelect.value = 'otro';
    eventModal.classList.add('open');
  }

  function openModalForEdit(fcEvent) {
    editingEvent = fcEvent;
    titleInput.value = fcEvent.title || '';
    dateInput.value  = toDateInputValue(fcEvent.start);
    const cls = (fcEvent.classNames && fcEvent.classNames[0]) || 'event-otro';
    const type = Object.entries(TYPE_TO_CLASS).find(([,v]) => v === cls)?.[0] || 'otro';
    typeSelect.value = type;
    eventModal.classList.add('open');
  }

  function closeModal() {
    eventModal.classList.remove('open');
  }
  const closeIcon = document.querySelector('#event-modal .close-modal-btn');
if (closeIcon) closeIcon.addEventListener('click', closeModal);


  function toDateInputValue(d) {
    if (!d) return '';
    const dt = new Date(d);
    dt.setMinutes(dt.getMinutes() - dt.getTimezoneOffset());
    return dt.toISOString().slice(0,10);
  }

  // --- Inicializa FullCalendar ---
  const calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: 'dayGridMonth',
    locale: 'es',
    selectable: true,
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },

    // Crear con selección o clic en día
    select: (info) => openModalForCreate(info.start),
    dateClick: (info) => openModalForCreate(info.date),

    // Editar haciendo clic en el evento
    eventClick: (info) => openModalForEdit(info.event),

    // Refrescar listado lateral al cambiar de fecha visible
    datesSet: () => {
      // Mantener el label con la fecha "seleccionada" (hoy por defecto)
      const today = new Date();
      updateDayEventsSidebar(today);
    },

    events: [] // empieza vacío; se puede persistir después si quieres
  });

  calendar.render();

  // Inicial: mostrar hoy en el panel
  updateDayEventsSidebar(new Date());

  // --- Botones superiores / modal ---
  if (addEventBtn) addEventBtn.addEventListener('click', () => openModalForCreate(new Date()));
  if (cancelBtn)   cancelBtn.addEventListener('click', closeModal);
  if (closeBtn)    closeBtn.addEventListener('click', closeModal);
  // Cerrar al hacer click fuera de la tarjeta
  eventModal.addEventListener('click', (e) => {
    if (e.target === eventModal) closeModal();
  });

  // --- Crear / Editar (submit del formulario) ---
  eventForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const title = titleInput.value.trim();
    const dateStr = dateInput.value;
    const type = typeSelect.value; // riego|poda|fertilizacion|otro

    if (!title || !dateStr) return;

    const start = new Date(dateStr);
    const className = TYPE_TO_CLASS[type] || 'event-otro';

    if (editingEvent) {
      // ---- EDITAR ----
      editingEvent.setProp('title', title);
      editingEvent.setStart(start);
      editingEvent.setAllDay(true);
      editingEvent.setProp('classNames', [className]);
    } else {
      // ---- CREAR ----
      calendar.addEvent({
        id: `evt_${Date.now()}_${Math.random().toString(36).slice(2)}`,
        title,
        start,
        allDay: true,
        classNames: [className]
      });
    }

    closeModal();
    updateDayEventsSidebar(start);
  });

  // ====== LADO DERECHO: lista de eventos del día ======
  function updateDayEventsSidebar(date) {
    if (!date) return;

    // Actualiza el título del día (ej: “Eventos para 2025-09-08”)
    const label = date.toLocaleDateString('es-PE', { year:'numeric', month:'long', day:'numeric' });
    if (selectedDayLbl) selectedDayLbl.textContent = label;

    // Filtra eventos que caen ese día (allDay)
    const events = calendar.getEvents().filter(ev => isSameDay(ev.start, date));

    dayEventsList.innerHTML = '';
    if (events.length === 0) {
      dayEventsList.innerHTML = `<li class="day-event-item event-otro">
        <div class="day-event-main">
          <i class="fas fa-circle-info"></i>
          <span class="title">No hay recordatorios para este día</span>
        </div>
      </li>`;
      return;
    }

    events.forEach(ev => {
      const cls = (ev.classNames && ev.classNames[0]) || 'event-otro';
      const icon = CLASS_TO_ICON[cls] || 'fa-check';

      const li = document.createElement('li');
      li.className = `day-event-item ${cls}`;
      li.innerHTML = `
        <div class="day-event-main">
          <i class="fas ${icon}"></i>
          <span class="title">${escapeHtml(ev.title || '')}</span>
        </div>
        <div class="day-event-actions">
          <button class="btn-icon edit" aria-label="Editar"><i class="fas fa-pen"></i></button>
          <button class="btn-icon delete" aria-label="Eliminar"><i class="fas fa-trash"></i></button>
        </div>
      `;

      // Editar
      li.querySelector('.edit').addEventListener('click', () => openModalForEdit(ev));
      // Eliminar
      li.querySelector('.delete').addEventListener('click', () => {
        if (confirm('¿Eliminar este recordatorio?')) {
          const d = new Date(ev.start);
          ev.remove();
          updateDayEventsSidebar(d);
        }
      });

      dayEventsList.appendChild(li);
    });
  }

  // Helpers de fecha y sanitización
  function isSameDay(a, b) {
    const d1 = new Date(a), d2 = new Date(b);
    return d1.getFullYear() === d2.getFullYear() &&
           d1.getMonth() === d2.getMonth() &&
           d1.getDate() === d2.getDate();
  }
  function escapeHtml(str) {
    return str.replace(/[&<>"']/g, (m) =>
      ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[m])
    );
  }

});
