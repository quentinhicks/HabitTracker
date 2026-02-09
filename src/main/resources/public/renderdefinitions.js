async function fetchHabs() {
    let res1 = await fetch("/addHabs");
    let addHabs = await res1.json();
    let res2 = await fetch("/habits");
    let habits = await res2.json();
    renderDefs(habits, addHabs);
}

function renderDefs(habits, addHabs) {
    const tbody = document.querySelector("#defTable tbody");
    tbody.innerHTML = "";

    habits.forEach((habit, habitIndex) => {
        let row = document.createElement("tr");

        // cell for the category of habit
        let catCell = document.createElement("td");
        if (habitIndex === 0) catCell.textContent = "Daily";
        row.appendChild(catCell)

        // cell for the habit information
        let nameCell = document.createElement("td");
        nameCell.textContent = [habit.id, "; ", habit.name].join("");
        row.appendChild(nameCell);

        // cell for the definition of habit
        let defCell = document.createElement("td");
        defCell.textContent = habit.opDef;
        row.appendChild(defCell);

        tbody.appendChild(row);
    });

    addHabs.forEach((addHab, addHabIndex) => {
        let row = document.createElement("tr");

        // cell for the category of habit
        let catCell = document.createElement("td");
        if (addHabIndex === 0) catCell.textContent = "Additional";
        row.appendChild(catCell)

        // cell for the habit information
        let nameCell = document.createElement("td");
        nameCell.textContent = [addHab.id, "; ", addHab.name].join("");
        row.appendChild(nameCell);

        // cell for the definition of habit
        let defCell = document.createElement("td");
        defCell.textContent = addHab.opDef;
        row.appendChild(defCell);

        tbody.appendChild(row);


    });
}

// Initial load
fetchHabs();
