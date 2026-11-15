"use client";
import { useCallback, useSyncExternalStore } from "react";
import { useI18n } from "../providers/I18nProvider";
import { ChevronDown, ChevronRight } from "lucide-react";
import { MENU_TREE, MenuNode } from "../menu/MenuRegistry";
import {
  getServerSnapshot,
  readSnapshot,
  subscribe,
  toggleExpanded,
} from "../menu/MenuStorage";
interface SidebarProps {
  activeId: string;
  onSelect: (id: string) => void;
}
export default function Sidebar({ activeId, onSelect }: SidebarProps) {
  const { locale } = useI18n();
  const isCn = locale === "cn";
  const uiState = useSyncExternalStore(
    subscribe,
    readSnapshot,
    getServerSnapshot,
  );
  const isExpanded = useCallback(
    (nodeId: string) => {
      if (nodeId in uiState.expanded) return uiState.expanded[nodeId];
      return MENU_TREE.some(
        (n) => n.id === nodeId && n.children?.some((c) => c.id === activeId),
      );
    },
    [uiState.expanded, activeId],
  );
  const handleToggle = (id: string) => {
    toggleExpanded(id, isExpanded(id));
  };
  const renderNode = (node: MenuNode, depth: number) => {
    const hasChildren = !!node.children?.length;
    const isActive = activeId === node.id;
    const isParentActive =
      hasChildren && node.children!.some((c) => c.id === activeId);
    const open = isExpanded(node.id);
    const baseClass =
      "w-full flex items-center justify-between gap-1.5 px-2 py-1 rounded text-xs cursor-pointer";
    const stateClass = isActive
      ? "bg-foreground text-background font-medium"
      : isParentActive
        ? "text-foreground"
        : "text-muted-foreground hover:bg-muted hover:text-foreground";
    return (
      <div key={node.id}>
        <button
          type="button"
          onClick={() => {
            if (hasChildren) {
              handleToggle(node.id);
            } else {
              onSelect(node.id);
            }
          }}
          className={`${baseClass} ${stateClass}`}
          style={{ paddingLeft: 8 + depth * 12 }}
        >
          <span className="truncate">{node.label[locale]}</span>
          {hasChildren &&
            (open ? (
              <ChevronDown className="w-3 h-3 shrink-0" />
            ) : (
              <ChevronRight className="w-3 h-3 shrink-0" />
            ))}
        </button>
        {hasChildren && open && (
          <div className="mt-0.5 space-y-0.5">
            {node.children!.map((child) => renderNode(child, depth + 1))}
          </div>
        )}
      </div>
    );
  };
  return (
    <aside className="h-full w-48 shrink-0 border-r border-border bg-background flex flex-col">
      <div className="px-2.5 py-2 border-b border-border">
        <span className="text-[10px] font-semibold text-muted-foreground uppercase tracking-wider">
          {isCn ? "菜单" : "Menu"}
        </span>
      </div>
      <nav className="flex-1 overflow-y-auto p-1.5 space-y-0.5">
        {MENU_TREE.map((node) => renderNode(node, 0))}
      </nav>
    </aside>
  );
}
