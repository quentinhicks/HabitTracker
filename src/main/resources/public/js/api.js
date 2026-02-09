export async function fetchAddHabs() {
    // return an empty array on failure
    return requestJSON("/addHabs", {}, []);
}

export async function addHabit(name) {
    // return null if adding habit fails
    return requestJSON("/addAddHab", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name })
    }, null);
}

export async function updateHabitStatus(index, completed) {
    // no need to return anything, but fallback can be null
    return requestJSON("/updateAddHabDay", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ addHabIndex: index, completed })
    }, null);
}
