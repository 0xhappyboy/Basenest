"use client";
import { useI18n } from "../providers/I18nProvider";
export default function Footer() {
  const { locale } = useI18n();
  const isCn = locale === "cn";
  return (
    <footer className="w-full shrink-0 border-t border-border bg-muted/60">
      <div className="px-4 py-1.5">
        {/* Single centered line, compact height */}
        <div className="flex flex-wrap items-center justify-center gap-x-1.5 gap-y-0.5 text-[10px] text-muted-foreground leading-tight">
          <span className="font-medium tracking-wide">
            Basenest SaaS Management System
          </span>
          <span className="text-muted-foreground/40">·</span>
          <span>
            {isCn
              ? "Basenest Saas 管理系统"
              : "Basenest SaaS Management System"}
          </span>
          <span className="text-muted-foreground/40">·</span>
          <span>© {new Date().getFullYear()}</span>
          <span className="text-muted-foreground/40">·</span>
          <a
            target="_blank"
            rel="noopener noreferrer"
            className="hover:text-foreground/70 transition-colors"
          >
            Apache-2.0
          </a>
          <span className="text-muted-foreground/40">·</span>
          <span>{isCn ? "开源" : "Open Source"}</span>
        </div>
      </div>
    </footer>
  );
}
