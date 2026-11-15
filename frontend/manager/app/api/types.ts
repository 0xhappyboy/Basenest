/* Shared TypeScript types for backend API payloads. */
/* ----- System user ----- */
export interface SystemUser {
    id?: number;
    username: string;
    password?: string;
    nickname?: string | null;
    realName?: string | null;
    email?: string | null;
    phone?: string | null;
    avatar?: string | null;
    gender?: number;
    dept?: string | null;
    jobTitle?: string | null;
    lastLoginTime?: string | null;
    lastLoginIp?: string | null;
    loginFailCount?: number;
    lockedUntil?: string | null;
    isSuper?: number;
    status?: number;
    isDeleted?: number;
    createTime?: string;
    updateTime?: string;
}
/* ----- System role ----- */
export interface SystemRole {
    id?: number;
    name: string;
    code: string;
    description?: string | null;
    dataScope?: number;
    isSystem?: number;
    sortOrder?: number;
    status?: number;
    isDeleted?: number;
    createTime?: string;
    updateTime?: string;
}
/* ----- System permission code ----- */
export interface SystemPermissionCode {
    id?: number;
    code: string;
    name: string;
    module: string;
    type?: string;
    description?: string | null;
    sortOrder?: number;
    status?: number;
    isDeleted?: number;
    createTime?: string;
    updateTime?: string;
}
/* ----- System menu ----- */
export interface SystemMenu {
    id?: number;
    parentId?: number;
    level?: number;
    name: string;
    code: string;
    type?: string;
    icon?: string | null;
    permission?: string | null;
    sortOrder?: number;
    visible?: number;
    status?: number;
    isDeleted?: number;
    createTime?: string;
    updateTime?: string;
}
/* ----- Common ----- */
export type Id = number | string;
export type ApiResult<T> = T;