CREATE DATABASE IF NOT EXISTS `basenest-manager` DEFAULT CHARACTER
SET
    utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;

USE `basenest-manager`;

-- ============================================================
-- Menu table
-- One row = one menu / directory / button node in the admin UI.
-- Max depth is 3 levels:
--   level 1 = top-level directory or page
--   level 2 = sub directory or page (parent_id -> level 1)
--   level 3 = page or button (parent_id -> level 2)
--
-- `parent_id` = 0 for level 1; otherwise it must point to a
-- node whose level is exactly one less than the current level.
-- No foreign keys are used, consistent with the schema style.
-- ============================================================
CREATE TABLE
    IF NOT EXISTS `t_manager_menu` (
        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
        `parent_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'Parent menu id, 0 = root (level 1)',
        `level` TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT 'Menu level: 1 / 2 / 3 (max 3)',
        `name` VARCHAR(64) NOT NULL COMMENT 'Display name',
        `code` VARCHAR(128) NOT NULL COMMENT 'Unique menu code, e.g. system:user:list',
        `type` VARCHAR(16) NOT NULL DEFAULT 'menu' COMMENT 'Type: directory / menu / button',
        `path` VARCHAR(255) DEFAULT NULL COMMENT 'Route path',
        `component` VARCHAR(255) DEFAULT NULL COMMENT 'Frontend component path',
        `icon` VARCHAR(128) DEFAULT NULL COMMENT 'Menu icon',
        `permission` VARCHAR(128) DEFAULT NULL COMMENT 'Required permission code',
        `sort_order` INT NOT NULL DEFAULT 0 COMMENT 'Display sort order',
        `visible` TINYINT NOT NULL DEFAULT 1 COMMENT 'Visible in menu: 0-hidden 1-visible',
        `status` TINYINT NOT NULL DEFAULT 1 COMMENT 'Status: 0-disabled 1-enabled',
        `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT 'Soft delete: 0-no 1-yes',
        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
        PRIMARY KEY (`id`),
        UNIQUE KEY `uk_code` (`code`),
        KEY `idx_parent_id` (`parent_id`),
        KEY `idx_level` (`level`),
        KEY `idx_status` (`status`),
        KEY `idx_is_deleted` (`is_deleted`),
        KEY `idx_sort_order` (`sort_order`),
        CONSTRAINT `chk_menu_level` CHECK (`level` BETWEEN 1 AND 3),
        CONSTRAINT `chk_menu_parent_level` CHECK (
            (
                `level` = 1
                AND `parent_id` = 0
            )
            OR (
                `level` > 1
                AND `parent_id` > 0
            )
        )
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Manager menu table (max 3 levels)';