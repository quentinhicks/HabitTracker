async function fetchAddHabs() {
    let res = await fetch("/habits");
    let addHabs = await res.json();
    renderAddHabs(addHabs);
}

function renderAddHabs(addHabs) {
    const tbody = document.querySelector("#addHabTable tbody");
    tbody.innerHTML = "";

    addHabs.forEach((addHab, addHabIndex) => {
        let row = document.createElement("tr");

        // Habit name
        let nameCell = document.createElement("td");
        nameCell.textContent = addHab.name;
        row.appendChild(nameCell);
        let weekCell = document.createElement("td");
        let checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.checked = addHab;
        checkbox.addEventListener("change", () => {
            fetch("/updateAddHabDay", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    addHabIndex,
                    completed: checkbox.checked
                })
            });
            weekCell.appendChild(checkbox);
            row.appendChild(weekCell);
        })

        tbody.appendChild(row);
    });
}

// Handle new habit form
document.getElementById("addHabForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    let addHabName = document.getElementById("addHabName").value.trim();
    if (!addHabName) return;

    await fetch("/addAddHab", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name: addHabName })
    });

    document.getElementById("addHabName").value = "";
    fetchAddHabs();
});

// Initial load
fetchAddHabs();
