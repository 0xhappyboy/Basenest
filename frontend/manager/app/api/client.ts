/* Thin HTTP client. One function per HTTP method. */
import { API_BASE_URL } from "./paths";
/* Generic request helper. Throws on non-2xx. */
async function request<T>(
    path: string,
    init?: RequestInit,
): Promise<T | null> {
    const url = path.startsWith("http") ? path : `${API_BASE_URL}${path}`;
    const res = await fetch(url, {
        ...init,
        headers: {
            "Content-Type": "application/json",
            ...(init?.headers ?? {}),
        },
        cache: "no-store",
    });
    if (!res.ok) {
        /* 404 returns null so callers can treat "not found" as empty. */
        if (res.status === 404) return null;
        const text = await res.text().catch(() => "");
        throw new Error(
            `Request failed: ${res.status} ${res.statusText} ${text}`,
        );
    }
    /* 204 / empty body -> null */
    if (res.status === 204) return null;
    const contentType = res.headers.get("content-type") ?? "";
    if (!contentType.includes("application/json")) {
        /* Some endpoints return plain text / booleans; try json, fallback to text. */
        const text = await res.text();
        if (text === "") return null;
        try {
            return JSON.parse(text) as T;
        } catch {
            return text as unknown as T;
        }
    }
    return (await res.json()) as T;
}
/* ----- HTTP method ----- */
export function get<T>(path: string): Promise<T | null> {
    return request<T>(path, { method: "GET" });
}
export function post<T, B = unknown>(path: string, body?: B): Promise<T | null> {
    return request<T>(path, {
        method: "POST",
        body: body === undefined ? undefined : JSON.stringify(body),
    });
}
export function put<T, B = unknown>(path: string, body?: B): Promise<T | null> {
    return request<T>(path, {
        method: "PUT",
        body: body === undefined ? undefined : JSON.stringify(body),
    });
}
export function del<T>(path: string): Promise<T | null> {
    return request<T>(path, { method: "DELETE" });
}
export function withQuery(
    path: string,
    query: Record<string, string | number | boolean | undefined | null>,
): string {
    const params = new URLSearchParams();
    Object.entries(query).forEach(([k, v]) => {
        if (v !== undefined && v !== null) params.append(k, String(v));
    });
    const qs = params.toString();
    return qs ? `${path}?${qs}` : path;
}