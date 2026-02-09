import { updateHabitStatus } from './api.js';

const tbody = document.querySelector("#addHabTable tbody");

export function renderAddHabs(addHabs) {
    tbody.innerHTML = "";

    const fragment = document.createDocumentFragment();
    addHabs.forEach((addHab, index) => {
        const row = document.createElement("tr");

        // Habit name cell
        const nameCell = document.createElement("td");
        nameCell.textContent = addHab.name;
        row.appendChild(nameCell);

        // Checkbox cell
        const weekCell = document.createElement("td");
        const checkbox = document.createElement("input");

        checkbox.type = "checkbox";
        checkbox.checked = addHab.completed;
        checkbox.addEventListener("change", () => updateHabitStatus(index, checkbox.checked));
        weekCell.appendChild(checkbox);
        row.appendChild(weekCell);

        fragment.appendChild(row);
    });

    tbody.appendChild(fragment);
}

export function setupAddHabForm(onSubmit) {
    const addHabForm = document.getElementById("addHabForm");
    const addHabNameInput = document.getElementById("addHabName");

    addHabForm.addEventListener("submit", async (e) => {
        e.preventDefault();
        const name = addHabNameInput.value.trim();
        if (!name) return;
        await onSubmit(name);
        addHabNameInput.value = "";
    });
}
