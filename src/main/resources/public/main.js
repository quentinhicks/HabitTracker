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
            dayCell.className = "check";
            if (!dayCell.classList.contains("check")) return;
            dayCell.dataset.state = habit.days[dayIndex]; // start transparent
            dayCell.classList.add(`state-${dayCell.dataset.state}`);
            dayCell.addEventListener("click", e => {
                // if (dayCell.className === "check") {
                //     console.log("Clicked td:", dayCell);
                // }
                // increment state 0→1→2→3→0
                let state = (parseInt(dayCell.dataset.state) + 1) % 4;
                dayCell.dataset.state = ""+state;
                // remove old classes
                dayCell.classList.remove("state-0", "state-1", "state-2", "state-3");
                // add new state class
                dayCell.classList.add(`state-${state}`);
                fetch("/updateHabitDay", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({
                        habitIndex,
                        dayIndex,
                        state: Number(dayCell.dataset.state)
                    })
                });
            });

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
