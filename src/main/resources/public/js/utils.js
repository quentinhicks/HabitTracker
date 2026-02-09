/**
 * Generic fetch helper with error handling and default fallback
 * @param {string} url - API endpoint
 * @param {object} options - fetch options (method, headers, body)
 * @param {*} fallback - value to return if fetch fails
 * @returns {*} - JSON response or fallback
 */

async function requestJSON(url, options = {}, fallback = null) {
    try {
        const res = await fetch(url, options);
        if (!res.ok) throw new Error(`${res.status} ${res.statusText}`);
        return await res.json();
    } catch (err) {
        console.error(`Error fetching ${url}:`, err.message);
        return fallback;
    }
}
