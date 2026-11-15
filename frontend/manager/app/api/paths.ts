/* Central API path registry. */
export const API_BASE_URL = "http://127.0.0.1:8080";
export const API_PATHS = {
    /* ----- System: user ----- */
    systemUser: {
        list: "/system/user",
        byId: (id: number | string) => `/system/user/${id}`,
        byUsername: (username: string) =>
            `/system/user/username/${encodeURIComponent(username)}`,
        search: "/system/user/search",
        roles: (id: number | string) => `/system/user/${id}/roles`,
        roleDetails: (id: number | string) => `/system/user/${id}/role-details`,
        permissions: (id: number | string) => `/system/user/${id}/permissions`,
        directPermissions: (id: number | string) =>
            `/system/user/${id}/permissions/direct`,
    },
    /* ----- System: role ----- */
    systemRole: {
        list: "/system/role",
        byId: (id: number | string) => `/system/role/${id}`,
        byCode: (code: string) => `/system/role/code/${encodeURIComponent(code)}`,
        permissions: (id: number | string) => `/system/role/${id}/permissions`,
    },
    /* ----- System: permission code ----- */
    systemPermissionCode: {
        list: "/system/permission-code",
        byId: (id: number | string) => `/system/permission-code/${id}`,
        byCode: (code: string) =>
            `/system/permission-code/code/${encodeURIComponent(code)}`,
        byModule: (module: string) =>
            `/system/permission-code/module/${encodeURIComponent(module)}`,
        byType: (type: string) =>
            `/system/permission-code/type/${encodeURIComponent(type)}`,
    },
    /* ----- System: menu ----- */
    systemMenu: {
        list: "/system/menu",
        byId: (id: number | string) => `/system/menu/${id}`,
        byCode: (code: string) => `/system/menu/code/${encodeURIComponent(code)}`,
        byParentId: (parentId: number | string) => `/system/menu/parent/${parentId}`,
        byPermission: (permission: string) =>
            `/system/menu/permission/${encodeURIComponent(permission)}`,
        byUserId: (userId: number | string) => `/system/menu/user/${userId}`,
    },
} as const;