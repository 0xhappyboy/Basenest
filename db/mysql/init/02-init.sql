CREATE DATABASE IF NOT EXISTS `basenest-system` DEFAULT CHARACTER
SET
    utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;

USE `basenest-system`;

-- ============================================================
-- System management database
-- ============================================================
-- ============================================================
-- System user table
-- ============================================================
CREATE TABLE
    IF NOT EXISTS `t_sys_user` (
        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
        `username` VARCHAR(64) NOT NULL COMMENT 'Login account',
        `password` VARCHAR(255) NOT NULL COMMENT 'Password (bcrypt hash)',
        `nickname` VARCHAR(64) DEFAULT NULL COMMENT 'Nickname',
        `real_name` VARCHAR(64) DEFAULT NULL COMMENT 'Real name',
        `email` VARCHAR(128) DEFAULT NULL COMMENT 'Email',
        `phone` VARCHAR(20) DEFAULT NULL COMMENT 'Phone number',
        `avatar` VARCHAR(512) DEFAULT NULL COMMENT 'Avatar URL',
        `gender` TINYINT NOT NULL DEFAULT 0 COMMENT 'Gender: 0-unknown 1-male 2-female 3-other',
        `dept` VARCHAR(64) DEFAULT NULL COMMENT 'Department',
        `job_title` VARCHAR(64) DEFAULT NULL COMMENT 'Job title',
        -- ----- Login & security -----
        `last_login_time` DATETIME DEFAULT NULL COMMENT 'Last login time',
        `last_login_ip` VARCHAR(64) DEFAULT NULL COMMENT 'Last login IP',
        `login_fail_count` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'Consecutive login failure count',
        `locked_until` DATETIME DEFAULT NULL COMMENT 'Lock expiry time, NULL = not locked',
        -- ----- Status & audit -----
        `is_super` TINYINT NOT NULL DEFAULT 0 COMMENT 'Is super admin: 0-no 1-yes',
        `status` TINYINT NOT NULL DEFAULT 1 COMMENT 'Status: 0-disabled 1-enabled',
        `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT 'Soft delete: 0-no 1-yes',
        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
        PRIMARY KEY (`id`),
        UNIQUE KEY `uk_username` (`username`),
        UNIQUE KEY `uk_email` (`email`),
        UNIQUE KEY `uk_phone` (`phone`),
        KEY `idx_status` (`status`),
        KEY `idx_is_deleted` (`is_deleted`),
        KEY `idx_is_super` (`is_super`),
        KEY `idx_create_time` (`create_time`),
        KEY `idx_last_login_time` (`last_login_time`)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'System user table (B-side accounts)';

-- ============================================================
-- System role table
-- ============================================================
CREATE TABLE
    IF NOT EXISTS `t_sys_role` (
        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
        `name` VARCHAR(64) NOT NULL COMMENT 'Role name',
        `code` VARCHAR(64) NOT NULL COMMENT 'Role code, e.g. SUPER_ADMIN / ADMIN / USER',
        `description` VARCHAR(255) DEFAULT NULL COMMENT 'Role description',
        `data_scope` TINYINT NOT NULL DEFAULT 1 COMMENT 'Data scope: 1-all 2-dept 3-self 4-custom',
        `is_system` TINYINT NOT NULL DEFAULT 0 COMMENT 'Is system built-in: 0-no 1-yes (built-in cannot be deleted)',
        `sort_order` INT NOT NULL DEFAULT 0 COMMENT 'Display sort order',
        `status` TINYINT NOT NULL DEFAULT 1 COMMENT 'Status: 0-disabled 1-enabled',
        `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT 'Soft delete: 0-no 1-yes',
        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
        PRIMARY KEY (`id`),
        UNIQUE KEY `uk_code` (`code`),
        KEY `idx_status` (`status`),
        KEY `idx_is_deleted` (`is_deleted`),
        KEY `idx_is_system` (`is_system`),
        KEY `idx_sort_order` (`sort_order`)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'System role table';

-- ============================================================
-- System permission code enum table
-- ============================================================
CREATE TABLE
    IF NOT EXISTS `t_sys_permission_code` (
        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
        `code` VARCHAR(128) NOT NULL COMMENT 'Unique permission code, e.g. system:user:list',
        `name` VARCHAR(64) NOT NULL COMMENT 'Permission name',
        `module` VARCHAR(64) NOT NULL COMMENT 'Module group, e.g. system / app / tenant',
        `type` VARCHAR(16) NOT NULL DEFAULT 'api' COMMENT 'Type: menu / button / api / data',
        `description` VARCHAR(255) DEFAULT NULL COMMENT 'Permission description',
        `sort_order` INT NOT NULL DEFAULT 0 COMMENT 'Display sort order',
        `status` TINYINT NOT NULL DEFAULT 1 COMMENT 'Status: 0-disabled 1-enabled',
        `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT 'Soft delete: 0-no 1-yes',
        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
        PRIMARY KEY (`id`),
        UNIQUE KEY `uk_code` (`code`),
        KEY `idx_module` (`module`),
        KEY `idx_type` (`type`),
        KEY `idx_status` (`status`),
        KEY `idx_is_deleted` (`is_deleted`),
        KEY `idx_sort_order` (`sort_order`)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'System permission code enum table (single source of truth)';

-- ============================================================
-- System user-role relation table
-- ============================================================
CREATE TABLE
    IF NOT EXISTS `t_sys_user_role` (
        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
        `user_id` BIGINT UNSIGNED NOT NULL COMMENT 'System user id (t_sys_user.id)',
        `role_id` BIGINT UNSIGNED NOT NULL COMMENT 'System role id (t_sys_role.id)',
        `status` TINYINT NOT NULL DEFAULT 1 COMMENT 'Status: 0-disabled 1-enabled',
        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
        PRIMARY KEY (`id`),
        UNIQUE KEY `uk_user_role` (`user_id`, `role_id`),
        KEY `idx_role_id` (`role_id`),
        KEY `idx_user_status` (`user_id`, `status`)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'System user-role relation table';

-- ============================================================
-- System role-permission relation table
-- ============================================================
CREATE TABLE
    IF NOT EXISTS `t_sys_role_permissions` (
        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
        `role_id` BIGINT UNSIGNED NOT NULL COMMENT 'System role id (t_sys_role.id)',
        `permission_code` VARCHAR(128) NOT NULL COMMENT 'Permission code (t_sys_permission_code.code)',
        `status` TINYINT NOT NULL DEFAULT 1 COMMENT 'Status: 0-disabled 1-enabled',
        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
        PRIMARY KEY (`id`),
        UNIQUE KEY `uk_role_permission` (`role_id`, `permission_code`),
        KEY `idx_permission_code` (`permission_code`),
        KEY `idx_role_status` (`role_id`, `status`)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'System role-permission relation table';

-- ============================================================
-- System user-permission relation table
-- ============================================================
CREATE TABLE
    IF NOT EXISTS `t_sys_user_permissions` (
        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
        `user_id` BIGINT UNSIGNED NOT NULL COMMENT 'System user id (t_sys_user.id)',
        `permission_code` VARCHAR(128) NOT NULL COMMENT 'Permission code (t_sys_permission_code.code)',
        `grant_type` TINYINT NOT NULL DEFAULT 1 COMMENT 'Grant type: 1-grant 2-deny',
        `status` TINYINT NOT NULL DEFAULT 1 COMMENT 'Status: 0-disabled 1-enabled',
        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
        PRIMARY KEY (`id`),
        UNIQUE KEY `uk_user_permission` (`user_id`, `permission_code`),
        KEY `idx_permission_code` (`permission_code`),
        KEY `idx_user_status` (`user_id`, `status`)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'System user-permission relation table';

-- ============================================================
-- Menu table
-- ============================================================
CREATE TABLE
    IF NOT EXISTS `t_sys_menu` (
        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
        `parent_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'Parent menu id, 0 = root (level 1)',
        `level` TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT 'Menu level: 1 / 2 / 3 (max 3)',
        `name` VARCHAR(64) NOT NULL COMMENT 'Display name',
        `code` VARCHAR(128) NOT NULL COMMENT 'Unique menu code, e.g. system:user:list',
        `type` VARCHAR(16) NOT NULL DEFAULT 'menu' COMMENT 'Type: directory / menu / button',
        `icon` VARCHAR(128) DEFAULT NULL COMMENT 'Menu icon',
        `permission` VARCHAR(128) DEFAULT NULL COMMENT 'Required permission code (t_sys_permission_code.code)',
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

-- ============================================================
-- Initialization data
-- ============================================================
-- ------------------------------------------------------------
-- Default super admin user
-- Password: Admin@123 (bcrypt hash, replace in production)
-- ------------------------------------------------------------
INSERT INTO
    `t_sys_user` (
        `id`,
        `username`,
        `password`,
        `nickname`,
        `real_name`,
        `email`,
        `is_super`,
        `status`,
        `is_deleted`
    )
VALUES
    (
        1,
        'admin',
        '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa',
        'Admin',
        'Administrator',
        'admin@basenest.local',
        1,
        1,
        0
    ) ON DUPLICATE KEY
UPDATE `nickname` =
VALUES
    (`nickname`);

-- ------------------------------------------------------------
-- System built-in roles
-- SUPER_ADMIN : super administrator (full control)
-- ADMIN       : administrator (manage operations, no destructive ops)
-- USER        : normal user (read-only / basic access)
-- ------------------------------------------------------------
INSERT INTO
    `t_sys_role` (
        `id`,
        `name`,
        `code`,
        `description`,
        `data_scope`,
        `is_system`,
        `sort_order`,
        `status`,
        `is_deleted`
    )
VALUES
    (
        1,
        'Super Admin',
        'SUPER_ADMIN',
        'Super administrator with full control',
        1,
        1,
        1,
        1,
        0
    ),
    (
        2,
        'Admin',
        'ADMIN',
        'Administrator for daily operations',
        2,
        1,
        2,
        1,
        0
    ),
    (
        3,
        'User',
        'USER',
        'Normal user with read-only access',
        3,
        1,
        3,
        1,
        0
    ) ON DUPLICATE KEY
UPDATE `name` =
VALUES
    (`name`);

-- ------------------------------------------------------------
-- Bind super admin user to super admin role
-- ------------------------------------------------------------
INSERT INTO
    `t_sys_user_role` (`user_id`, `role_id`, `status`)
VALUES
    (1, 1, 1) ON DUPLICATE KEY
UPDATE `status` =
VALUES
    (`status`);

-- ------------------------------------------------------------
-- Permission code enum (system module)
-- ------------------------------------------------------------
INSERT INTO
    `t_sys_permission_code` (
        `id`,
        `code`,
        `name`,
        `module`,
        `type`,
        `description`,
        `sort_order`,
        `status`,
        `is_deleted`
    )
VALUES
    (
        1,
        'system',
        'System',
        'system',
        'menu',
        'System management',
        1,
        1,
        0
    ),
    (
        2,
        'system:user',
        'User',
        'system',
        'menu',
        'User management',
        1,
        1,
        0
    ),
    (
        3,
        'system:user:list',
        'User List',
        'system',
        'api',
        'Query user list',
        1,
        1,
        0
    ),
    (
        4,
        'system:user:create',
        'User Create',
        'system',
        'api',
        'Create user',
        2,
        1,
        0
    ),
    (
        5,
        'system:user:update',
        'User Update',
        'system',
        'api',
        'Update user',
        3,
        1,
        0
    ),
    (
        6,
        'system:user:delete',
        'User Delete',
        'system',
        'api',
        'Delete user',
        4,
        1,
        0
    ),
    (
        7,
        'system:role',
        'Role',
        'system',
        'menu',
        'Role management',
        2,
        1,
        0
    ),
    (
        8,
        'system:role:list',
        'Role List',
        'system',
        'api',
        'Query role list',
        1,
        1,
        0
    ),
    (
        9,
        'system:role:create',
        'Role Create',
        'system',
        'api',
        'Create role',
        2,
        1,
        0
    ),
    (
        10,
        'system:role:update',
        'Role Update',
        'system',
        'api',
        'Update role',
        3,
        1,
        0
    ),
    (
        11,
        'system:role:delete',
        'Role Delete',
        'system',
        'api',
        'Delete role',
        4,
        1,
        0
    ),
    (
        12,
        'system:permission',
        'Permission',
        'system',
        'menu',
        'Permission management',
        3,
        1,
        0
    ),
    (
        13,
        'system:permission:list',
        'Permission List',
        'system',
        'api',
        'Query permission list',
        1,
        1,
        0
    ),
    (
        14,
        'system:permission:create',
        'Permission Create',
        'system',
        'api',
        'Create permission',
        2,
        1,
        0
    ),
    (
        15,
        'system:permission:update',
        'Permission Update',
        'system',
        'api',
        'Update permission',
        3,
        1,
        0
    ),
    (
        16,
        'system:permission:delete',
        'Permission Delete',
        'system',
        'api',
        'Delete permission',
        4,
        1,
        0
    ),
    (
        17,
        'system:menu',
        'Menu',
        'system',
        'menu',
        'Menu management',
        4,
        1,
        0
    ),
    (
        18,
        'system:menu:list',
        'Menu List',
        'system',
        'api',
        'Query menu list',
        1,
        1,
        0
    ),
    (
        19,
        'system:menu:create',
        'Menu Create',
        'system',
        'api',
        'Create menu',
        2,
        1,
        0
    ),
    (
        20,
        'system:menu:update',
        'Menu Update',
        'system',
        'api',
        'Update menu',
        3,
        1,
        0
    ),
    (
        21,
        'system:menu:delete',
        'Menu Delete',
        'system',
        'api',
        'Delete menu',
        4,
        1,
        0
    ) ON DUPLICATE KEY
UPDATE `name` =
VALUES
    (`name`);

-- ------------------------------------------------------------
-- Role-permission bindings
-- SUPER_ADMIN: all permission codes
-- ADMIN      : all except delete permissions
-- USER       : list / query only
-- ------------------------------------------------------------
INSERT INTO
    `t_sys_role_permissions` (`role_id`, `permission_code`, `status`)
VALUES
    -- SUPER_ADMIN: all
    (1, 'system', 1),
    (1, 'system:user', 1),
    (1, 'system:user:list', 1),
    (1, 'system:user:create', 1),
    (1, 'system:user:update', 1),
    (1, 'system:user:delete', 1),
    (1, 'system:role', 1),
    (1, 'system:role:list', 1),
    (1, 'system:role:create', 1),
    (1, 'system:role:update', 1),
    (1, 'system:role:delete', 1),
    (1, 'system:permission', 1),
    (1, 'system:permission:list', 1),
    (1, 'system:permission:create', 1),
    (1, 'system:permission:update', 1),
    (1, 'system:permission:delete', 1),
    (1, 'system:menu', 1),
    (1, 'system:menu:list', 1),
    (1, 'system:menu:create', 1),
    (1, 'system:menu:update', 1),
    (1, 'system:menu:delete', 1),
    -- ADMIN: all except delete
    (2, 'system', 1),
    (2, 'system:user', 1),
    (2, 'system:user:list', 1),
    (2, 'system:user:create', 1),
    (2, 'system:user:update', 1),
    (2, 'system:role', 1),
    (2, 'system:role:list', 1),
    (2, 'system:role:create', 1),
    (2, 'system:role:update', 1),
    (2, 'system:permission', 1),
    (2, 'system:permission:list', 1),
    (2, 'system:permission:create', 1),
    (2, 'system:permission:update', 1),
    (2, 'system:menu', 1),
    (2, 'system:menu:list', 1),
    (2, 'system:menu:create', 1),
    (2, 'system:menu:update', 1),
    -- USER: list only
    (3, 'system', 1),
    (3, 'system:user', 1),
    (3, 'system:user:list', 1),
    (3, 'system:role', 1),
    (3, 'system:role:list', 1),
    (3, 'system:permission', 1),
    (3, 'system:permission:list', 1),
    (3, 'system:menu', 1),
    (3, 'system:menu:list', 1) ON DUPLICATE KEY
UPDATE `status` =
VALUES
    (`status`);

-- ------------------------------------------------------------
-- Built-in menus (system module, max 3 levels)
-- Path and component are not stored; frontend maps code -> page.
-- ------------------------------------------------------------
INSERT INTO
    `t_sys_menu` (
        `id`,
        `parent_id`,
        `level`,
        `name`,
        `code`,
        `type`,
        `icon`,
        `permission`,
        `sort_order`,
        `visible`,
        `status`,
        `is_deleted`
    )
VALUES
    (
        1,
        0,
        1,
        'System',
        'system',
        'directory',
        'SettingOutlined',
        NULL,
        1,
        1,
        1,
        0
    ),
    (
        2,
        1,
        2,
        'User',
        'system:user:list',
        'menu',
        'UserOutlined',
        'system:user:list',
        1,
        1,
        1,
        0
    ),
    (
        3,
        1,
        2,
        'Role',
        'system:role:list',
        'menu',
        'TeamOutlined',
        'system:role:list',
        2,
        1,
        1,
        0
    ),
    (
        4,
        1,
        2,
        'Permission',
        'system:permission:list',
        'menu',
        'SafetyOutlined',
        'system:permission:list',
        3,
        1,
        1,
        0
    ),
    (
        5,
        1,
        2,
        'Menu',
        'system:menu:list',
        'menu',
        'MenuOutlined',
        'system:menu:list',
        4,
        1,
        1,
        0
    ),
    (
        6,
        2,
        3,
        'User Create',
        'system:user:create',
        'button',
        NULL,
        'system:user:create',
        1,
        1,
        1,
        0
    ),
    (
        7,
        2,
        3,
        'User Update',
        'system:user:update',
        'button',
        NULL,
        'system:user:update',
        2,
        1,
        1,
        0
    ),
    (
        8,
        2,
        3,
        'User Delete',
        'system:user:delete',
        'button',
        NULL,
        'system:user:delete',
        3,
        1,
        1,
        0
    ),
    (
        9,
        3,
        3,
        'Role Create',
        'system:role:create',
        'button',
        NULL,
        'system:role:create',
        1,
        1,
        1,
        0
    ),
    (
        10,
        3,
        3,
        'Role Update',
        'system:role:update',
        'button',
        NULL,
        'system:role:update',
        2,
        1,
        1,
        0
    ),
    (
        11,
        3,
        3,
        'Role Delete',
        'system:role:delete',
        'button',
        NULL,
        'system:role:delete',
        3,
        1,
        1,
        0
    ),
    (
        12,
        4,
        3,
        'Permission Create',
        'system:permission:create',
        'button',
        NULL,
        'system:permission:create',
        1,
        1,
        1,
        0
    ),
    (
        13,
        4,
        3,
        'Permission Update',
        'system:permission:update',
        'button',
        NULL,
        'system:permission:update',
        2,
        1,
        1,
        0
    ),
    (
        14,
        4,
        3,
        'Permission Delete',
        'system:permission:delete',
        'button',
        NULL,
        'system:permission:delete',
        3,
        1,
        1,
        0
    ),
    (
        15,
        5,
        3,
        'Menu Create',
        'system:menu:create',
        'button',
        NULL,
        'system:menu:create',
        1,
        1,
        1,
        0
    ),
    (
        16,
        5,
        3,
        'Menu Update',
        'system:menu:update',
        'button',
        NULL,
        'system:menu:update',
        2,
        1,
        1,
        0
    ),
    (
        17,
        5,
        3,
        'Menu Delete',
        'system:menu:delete',
        'button',
        NULL,
        'system:menu:delete',
        3,
        1,
        1,
        0
    ) ON DUPLICATE KEY
UPDATE `name` =
VALUES
    (`name`);