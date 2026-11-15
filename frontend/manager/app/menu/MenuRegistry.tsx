"use client";
import dynamic from "next/dynamic";
import type { ComponentType } from "react";
/* Lazy-loaded page components for each menu item.
 * Add a new menu here, then add a matching line in MENU_REGISTRY below. */
const DashboardPanel = dynamic(() => import("../pages/DashboardPanel"), {
  loading: () => <PanelLoading />,
});
const SystemUserPanel = dynamic(() => import("../pages/SystemUserPanel"), {
  loading: () => <PanelLoading />,
});
const SystemRolePanel = dynamic(() => import("../pages/SystemRolePanel"), {
  loading: () => <PanelLoading />,
});
const SystemMenuPanel = dynamic(() => import("../pages/SystemMenuPanel"), {
  loading: () => <PanelLoading />,
});
const SettingsPanel = dynamic(() => import("../pages/SettingsPanel"), {
  loading: () => <PanelLoading />,
});
function PanelLoading() {
  return <div className="p-6 text-sm text-muted-foreground">Loading...</div>;
}
/* Menu tree definition (2 levels).
 * `id` is the stable key used both by the sidebar and the right panel. */
export interface MenuNode {
  id: string;
  label: { cn: string; en: string };
  icon?: string;
  children?: MenuNode[];
}
export const MENU_TREE: MenuNode[] = [
  {
    id: "dashboard",
    label: { cn: "仪表盘", en: "Dashboard" },
  },
  {
    id: "system",
    label: { cn: "系统管理", en: "System" },
    children: [
      { id: "system:user", label: { cn: "用户管理", en: "Users" } },
      { id: "system:role", label: { cn: "角色管理", en: "Roles" } },
      { id: "system:menu", label: { cn: "菜单管理", en: "Menus" } },
    ],
  },
  {
    id: "settings",
    label: { cn: "设置", en: "Settings" },
  },
];
/* id -> component registry. One line per menu, keep it tidy. */
export const MENU_COMPONENTS: Record<string, ComponentType> = {
  dashboard: DashboardPanel,
  "system:user": SystemUserPanel,
  "system:role": SystemRolePanel,
  "system:menu": SystemMenuPanel,
  settings: SettingsPanel,
};
export const DEFAULT_MENU_ID = "dashboard";
