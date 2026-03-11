const API_BASE = 'http://localhost:8081/api';

function setToken(token) { localStorage.setItem('token', token); }
function getToken() { return localStorage.getItem('token'); }

async function apiCall(endpoint, method = 'GET', body) {
  const headers = { 'Content-Type': 'application/json' };
  const token = getToken();
  if (token) headers['Authorization'] = `Bearer ${token}`;

  const response = await fetch(`${API_BASE}${endpoint}`, {
    method,
    headers,
    body: body ? JSON.stringify(body) : undefined,
  });

  if (!response.ok) {
    const err = await response.json();
    throw new Error(err.error || 'Request failed');
  }
  return response.json();
}
