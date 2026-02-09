async function fetchHabits() {
    let res = await fetch("/habits");
    let habits = await res.json();
    renderHabits(habits);
}

function renderHabits(habits) {
    const tbody = document.querySelector("#habitTable tbody");
    tbody.innerHTML = "";

    habits.forEach((habit, habitIndex) => {
        let row = document.createElement("tr");

        // Habit name
        let nameCell = document.createElement("td");
        nameCell.textContent = habit.name;
        row.appendChild(nameCell);

        // 7 days checkboxes
        for (let dayIndex = 0; dayIndex < 7; dayIndex++) {
            let dayCell = document.createElement("td");
            let checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.checked = habit.days[dayIndex];
            checkbox.addEventListener("change", () => {
                fetch("/updateHabitDay", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({
                        habitIndex,
                        dayIndex,
                        completed: checkbox.checked
                    })
                });
            });
            dayCell.appendChild(checkbox);
            row.appendChild(dayCell);
        }

        tbody.appendChild(row);
    });
}

// Handle new habit form
document.getElementById("habitForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    let habitName = document.getElementById("habitName").value.trim();
    if (!habitName) return;

    await fetch("/addHabit", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name: habitName })
    });

    document.getElementById("habitName").value = "";
    fetchHabits();
});

// Initial load
fetchHabits();
