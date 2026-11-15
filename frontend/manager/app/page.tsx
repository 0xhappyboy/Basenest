"use client";
import { useState, useEffect, useSyncExternalStore } from "react";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Sidebar from "./components/Sidebar";
import { ArrowUp } from "lucide-react";
import { DEFAULT_MENU_ID, MENU_COMPONENTS } from "./menu/MenuRegistry";
import {
  getServerSnapshot,
  readSnapshot,
  setActiveId,
  subscribe,
} from "./menu/MenuStorage";
export default function HomePage() {
  const [sidebarOpen, setSidebarOpen] = useState(true);
  const [showScrollTop, setShowScrollTop] = useState(false);
  const uiState = useSyncExternalStore(
    subscribe,
    readSnapshot,
    getServerSnapshot,
  );
  const activeMenuId = uiState.activeId ?? DEFAULT_MENU_ID;
  useEffect(() => {
    const scrollContainer = document.querySelector(".scrollable-content");
    if (!scrollContainer) return;
    const handleScroll = () => {
      setShowScrollTop(scrollContainer.scrollTop > 300);
    };
    scrollContainer.addEventListener("scroll", handleScroll);
    return () => scrollContainer.removeEventListener("scroll", handleScroll);
  }, []);
  const scrollToTop = () => {
    const scrollContainer = document.querySelector(".scrollable-content");
    if (scrollContainer) {
      scrollContainer.scrollTo({
        top: 0,
        behavior: "smooth",
      });
    }
  };
  const ActivePanel =
    MENU_COMPONENTS[activeMenuId] ?? MENU_COMPONENTS[DEFAULT_MENU_ID];
  return (
    <div className="h-screen bg-background text-foreground flex flex-col overflow-hidden">
      <Header
        sidebarOpen={sidebarOpen}
        onToggleSidebar={() => setSidebarOpen((v) => !v)}
      />
      <div className="flex-1 flex overflow-hidden">
        {sidebarOpen && (
          <Sidebar activeId={activeMenuId} onSelect={setActiveId} />
        )}
        <div className="flex-1 flex flex-col overflow-hidden">
          <div className="flex-1 overflow-y-auto scrollable-content">
            <div className="min-h-full flex flex-col">
              <div className="flex-1">
                <ActivePanel />
              </div>
            </div>
          </div>
          <Footer />
        </div>
      </div>
      <button
        onClick={scrollToTop}
        className={`
          fixed bottom-8 right-8 z-50
          w-12 h-12 rounded-full
          bg-black text-white
          border border-gray-300
          shadow-[0_4px_12px_rgba(0,0,0,0.15)]
          hover:bg-gray-800 hover:shadow-[0_6px_20px_rgba(0,0,0,0.25)]
          active:bg-gray-900
          transition-all duration-300 ease-in-out
          flex items-center justify-center
          cursor-pointer
          ${
            showScrollTop
              ? "opacity-100 translate-y-0"
              : "opacity-0 translate-y-16 pointer-events-none"
          }
        `}
        aria-label="Scroll to top"
      >
        <ArrowUp className="w-5 h-5" />
      </button>
    </div>
  );
}
