const STORAGE_KEY = "sidebar.uiState";
export interface SidebarUiState {
    activeId: string | null;
    expanded: Record<string, boolean>;
}
const EMPTY: SidebarUiState = { activeId: null, expanded: {} };
const listeners = new Set<() => void>();
/* Cache so getSnapshot returns a stable reference. */
let cachedRaw: string | null = null;
let cachedValue: SidebarUiState = EMPTY;
function parse(raw: string | null): SidebarUiState {
    if (!raw) return EMPTY;
    try {
        const parsed = JSON.parse(raw);
        if (parsed && typeof parsed === "object") {
            const activeId =
                typeof parsed.activeId === "string" ? parsed.activeId : null;
            const expanded =
                parsed.expanded && typeof parsed.expanded === "object"
                    ? (parsed.expanded as Record<string, boolean>)
                    : {};
            return { activeId, expanded };
        }
    } catch {
        /* ignore malformed storage */
    }
    return EMPTY;
}
export function readSnapshot(): SidebarUiState {
    if (typeof window === "undefined") return EMPTY;
    const raw = window.localStorage.getItem(STORAGE_KEY);
    if (raw === cachedRaw) return cachedValue;
    cachedRaw = raw;
    cachedValue = parse(raw);
    return cachedValue;
}
export function getServerSnapshot(): SidebarUiState {
    return EMPTY;
}
export function subscribe(cb: () => void): () => void {
    listeners.add(cb);
    const onStorage = (e: StorageEvent) => {
        if (e.key === STORAGE_KEY) {
            cachedRaw = null;
            cb();
        }
    };
    window.addEventListener("storage", onStorage);
    return () => {
        listeners.delete(cb);
        window.removeEventListener("storage", onStorage);
    };
}
export function writeState(next: SidebarUiState) {
    if (typeof window === "undefined") return;
    try {
        window.localStorage.setItem(STORAGE_KEY, JSON.stringify(next));
    } catch {
        /* ignore quota / privacy mode errors */
    }
    cachedRaw = null;
    listeners.forEach((l) => l());
}
/* Convenience helpers for partial updates. */
export function setActiveId(id: string) {
    const current = readSnapshot();
    writeState({ ...current, activeId: id });
}
export function toggleExpanded(id: string, currentlyExpanded: boolean) {
    const current = readSnapshot();
    writeState({
        ...current,
        expanded: { ...current.expanded, [id]: !currentlyExpanded },
    });
}