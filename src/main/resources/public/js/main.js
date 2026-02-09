// main.js
import { fetchAddHabs, addHabit } from './api.js';
import { renderAddHabs, setupAddHabForm } from './ui.js';

async function loadHabits() {
    const addHabs = await fetchAddHabs();
    renderAddHabs(addHabs);
}

// Setup form submission
setupAddHabForm(async (name) => {
    await addHabit(name);
    await loadHabits(); // Refresh table after adding
});

// Initial load
loadHabits();
