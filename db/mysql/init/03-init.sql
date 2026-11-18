CREATE DATABASE IF NOT EXISTS `basenest-system` DEFAULT CHARACTER
SET
    utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;

USE `basenest-system`;

-- ============================================================
-- AI vendor table
-- ============================================================
CREATE TABLE
    IF NOT EXISTS `t_sys_ai_vendor` (
        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
        `vendor_code` VARCHAR(64) NOT NULL COMMENT 'Vendor code matching Rust enum variant, e.g. OpenAI / Anthropic / Seedance',
        `name_cn` VARCHAR(128) NOT NULL COMMENT 'Vendor name in Chinese',
        `name_en` VARCHAR(128) NOT NULL COMMENT 'Vendor name in English',
        `description` VARCHAR(512) DEFAULT NULL COMMENT 'Vendor description',
        `website` VARCHAR(256) DEFAULT NULL COMMENT 'Official website URL',
        `sort_order` INT NOT NULL DEFAULT 0 COMMENT 'Display sort order',
        `status` TINYINT NOT NULL DEFAULT 1 COMMENT 'Status: 0-disabled 1-enabled',
        `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT 'Soft delete: 0-no 1-yes',
        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
        PRIMARY KEY (`id`),
        UNIQUE KEY `uk_vendor_code` (`vendor_code`),
        KEY `idx_status` (`status`),
        KEY `idx_is_deleted` (`is_deleted`),
        KEY `idx_sort_order` (`sort_order`)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'AI vendor table';

-- ============================================================
-- AI model table
-- ============================================================
CREATE TABLE
    IF NOT EXISTS `t_sys_ai_model` (
        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
        `vendor_id` BIGINT UNSIGNED NOT NULL COMMENT 'Vendor id (t_sys_ai_vendor.id)',
        `model_code` VARCHAR(128) NOT NULL COMMENT 'Model code, e.g. gpt-4o / claude-sonnet-4 / seedance-2-0',
        `model_name` VARCHAR(128) NOT NULL COMMENT 'Human-readable model name',
        `modality` VARCHAR(16) NOT NULL COMMENT 'Modality: chat / image / video / audio',
        `provider_code` VARCHAR(64) DEFAULT NULL COMMENT 'Provider enum variant in Rust, e.g. OpenAI / Seedance / Veo',
        `description` VARCHAR(512) DEFAULT NULL COMMENT 'Model description',
        `sort_order` INT NOT NULL DEFAULT 0 COMMENT 'Display sort order',
        `status` TINYINT NOT NULL DEFAULT 1 COMMENT 'Status: 0-disabled 1-enabled',
        `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT 'Soft delete: 0-no 1-yes',
        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
        PRIMARY KEY (`id`),
        UNIQUE KEY `uk_vendor_model` (`vendor_id`, `model_code`),
        KEY `idx_vendor_id` (`vendor_id`),
        KEY `idx_modality` (`modality`),
        KEY `idx_provider_code` (`provider_code`),
        KEY `idx_status` (`status`),
        KEY `idx_is_deleted` (`is_deleted`),
        KEY `idx_sort_order` (`sort_order`),
        CONSTRAINT `fk_ai_model_vendor` FOREIGN KEY (`vendor_id`) REFERENCES `t_sys_ai_vendor` (`id`)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'AI model table (chat / image / video / audio)';

-- ============================================================
-- AI model billing table
-- ============================================================
CREATE TABLE
    IF NOT EXISTS `t_sys_ai_model_billing` (
        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
        `vendor_id` BIGINT UNSIGNED NOT NULL COMMENT 'Vendor id (t_sys_ai_vendor.id)',
        `model_id` BIGINT UNSIGNED NOT NULL COMMENT 'Model id (t_sys_ai_model.id)',
        `billing_unit` VARCHAR(16) NOT NULL COMMENT 'Billing unit: image / second / k_token',
        `unit_price_usd` DECIMAL(16, 8) NOT NULL DEFAULT 0.00000000 COMMENT 'Unit price in USD',
        `currency` VARCHAR(8) NOT NULL DEFAULT 'USD' COMMENT 'Pricing currency, default USD',
        `effective_from` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Effective start time',
        `effective_to` DATETIME DEFAULT NULL COMMENT 'Effective end time, NULL = currently active',
        `status` TINYINT NOT NULL DEFAULT 1 COMMENT 'Status: 0-disabled 1-enabled',
        `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT 'Soft delete: 0-no 1-yes',
        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
        PRIMARY KEY (`id`),
        UNIQUE KEY `uk_model_billing_active` (`model_id`, `effective_from`),
        KEY `idx_vendor_id` (`vendor_id`),
        KEY `idx_model_id` (`model_id`),
        KEY `idx_billing_unit` (`billing_unit`),
        KEY `idx_status` (`status`),
        KEY `idx_is_deleted` (`is_deleted`),
        CONSTRAINT `fk_ai_billing_vendor` FOREIGN KEY (`vendor_id`) REFERENCES `t_sys_ai_vendor` (`id`),
        CONSTRAINT `fk_ai_billing_model` FOREIGN KEY (`model_id`) REFERENCES `t_sys_ai_model` (`id`)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'AI model billing table (per-unit USD price)';

-- ============================================================
-- AI model credit rate table
-- ============================================================
CREATE TABLE
    IF NOT EXISTS `t_sys_ai_model_credit_rate` (
        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
        `model_id` BIGINT UNSIGNED NOT NULL COMMENT 'Model id (t_sys_ai_model.id)',
        `credit_per_unit` DECIMAL(16, 8) NOT NULL DEFAULT 1.00000000 COMMENT 'Credits consumed per billing unit of the model',
        `effective_from` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Effective start time',
        `effective_to` DATETIME DEFAULT NULL COMMENT 'Effective end time, NULL = currently active',
        `status` TINYINT NOT NULL DEFAULT 1 COMMENT 'Status: 0-disabled 1-enabled',
        `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT 'Soft delete: 0-no 1-yes',
        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
        PRIMARY KEY (`id`),
        UNIQUE KEY `uk_model_credit_active` (`model_id`, `effective_from`),
        KEY `idx_model_id` (`model_id`),
        KEY `idx_status` (`status`),
        KEY `idx_is_deleted` (`is_deleted`),
        CONSTRAINT `fk_ai_credit_rate_model` FOREIGN KEY (`model_id`) REFERENCES `t_sys_ai_model` (`id`)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'AI credit-to-model billing rate table (adjustable)';

-- ============================================================
-- Currency exchange rate table
-- ============================================================
CREATE TABLE
    IF NOT EXISTS `t_sys_currency_exchange_rate` (
        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
        `currency_code` VARCHAR(8) NOT NULL COMMENT 'ISO 4217 currency code, e.g. USD / CNY / EUR',
        `currency_name` VARCHAR(64) NOT NULL COMMENT 'Currency display name',
        `symbol` VARCHAR(8) DEFAULT NULL COMMENT 'Currency symbol, e.g. $ / ¥ / €',
        `credit_per_unit` DECIMAL(16, 8) NOT NULL DEFAULT 1.00000000 COMMENT 'Credits per 1 unit of this currency',
        `effective_from` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Effective start time',
        `effective_to` DATETIME DEFAULT NULL COMMENT 'Effective end time, NULL = currently active',
        `status` TINYINT NOT NULL DEFAULT 1 COMMENT 'Status: 0-disabled 1-enabled',
        `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT 'Soft delete: 0-no 1-yes',
        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
        PRIMARY KEY (`id`),
        UNIQUE KEY `uk_currency_active` (`currency_code`, `effective_from`),
        KEY `idx_currency_code` (`currency_code`),
        KEY `idx_status` (`status`),
        KEY `idx_is_deleted` (`is_deleted`)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Fiat currency to credit exchange rate table (adjustable)';

-- ------------------------------------------------------------
-- AI vendors
-- vendor_code aligns with Rust enum variant names (PascalCase).
-- name_cn / name_en are kept distinct per vendor.
-- ------------------------------------------------------------
INSERT INTO
    `t_sys_ai_vendor` (`id`, `vendor_code`, `name_cn`, `name_en`, `description`, `website`, `sort_order`, `status`, `is_deleted`)
VALUES
    (1, 'OpenAI', '开放人工智能', 'OpenAI', 'GPT and o-series reasoning models', 'https://openai.com', 1, 1, 0),
    (2, 'Anthropic', '安思罗匹克', 'Anthropic', 'Claude safety-focused models', 'https://anthropic.com', 2, 1, 0),
    (3, 'Google', '谷歌', 'Google', 'Gemini multimodal, Veo video, Imagen image, Lyria music', 'https://ai.google.dev', 3, 1, 0),
    (4, 'DeepSeek', '深度求索', 'DeepSeek', 'Chat, coder and reasoning models', 'https://deepseek.com', 4, 1, 0),
    (5, 'Cohere', '科赫尔', 'Cohere', 'Enterprise Command models', 'https://cohere.com', 5, 1, 0),
    (6, 'HuggingFace', '拥抱脸', 'HuggingFace', 'Hosted open-source models', 'https://huggingface.co', 6, 1, 0),
    (7, 'Microsoft', '微软', 'Microsoft', 'Azure-hosted OpenAI models', 'https://azure.microsoft.com', 7, 1, 0),
    (8, 'Mistral', '米斯特拉尔', 'Mistral AI', 'Efficient European models', 'https://mistral.ai', 8, 1, 0),
    (9, 'Groq', '格罗克', 'Groq', 'Ultra-low-latency LPU inference', 'https://groq.com', 9, 1, 0),
    (10, 'Together', '同心智能', 'Together AI', 'Hosted open-source models', 'https://together.ai', 10, 1, 0),
    (11, 'Replicate', '复刻', 'Replicate', 'Cloud open-source model runner', 'https://replicate.com', 11, 1, 0),
    (12, 'Fireworks', '烟花智能', 'Fireworks AI', 'Fast open-source inference', 'https://fireworks.ai', 12, 1, 0),
    (13, 'Perplexity', '困惑度', 'Perplexity', 'Online-search-augmented models', 'https://perplexity.ai', 13, 1, 0),
    (14, 'Baidu', '百度', 'Baidu', 'ERNIE Chinese-first models', 'https://cloud.baidu.com', 14, 1, 0),
    (15, 'Alibaba', '阿里巴巴', 'Alibaba', 'Qwen Tongyi, Wan video, Qwen TTS', 'https://alibabacloud.com', 15, 1, 0),
    (16, 'Tencent', '腾讯', 'Tencent', 'Hunyuan Chinese models', 'https://cloud.tencent.com', 16, 1, 0),
    (17, 'Zhipu', '智谱', 'Zhipu AI', 'GLM Chinese-first models', 'https://zhipuai.cn', 17, 1, 0),
    (18, 'MiniMax', '稀宇科技', 'MiniMax', 'abab chat and Hailuo video models', 'https://minimax.io', 18, 1, 0),
    (19, 'Moonshot', '月之暗面', 'Moonshot AI', 'Kimi long-context models', 'https://moonshot.cn', 19, 1, 0),
    (20, 'Baichuan', '百川智能', 'Baichuan AI', 'Chinese-first models', 'https://baichuan-ai.com', 20, 1, 0),
    (21, 'Yi', '零一万物', '01.AI', 'Yi bilingual models', 'https://01.ai', 21, 1, 0),
    (22, 'Custom', '自定义', 'Custom', 'User-defined OpenAI-compatible endpoint', NULL, 22, 1, 0),
    (23, 'Seedance', '豆包 Seedance', 'ByteDance Seedance', 'Text-to-video generation', 'https://seed.bytedance.com', 23, 1, 0),
    (24, 'Wan', '通义万相', 'Alibaba Wan', 'Text-to-video and image-to-video', 'https://alibabacloud.com', 24, 1, 0),
    (25, 'Kling', '可灵', 'Kling AI', 'Video generation by Kuaishou', 'https://klingai.com', 25, 1, 0),
    (26, 'Veo', '维奥', 'Google Veo', 'High-quality video generation', 'https://deepmind.google/technologies/veo', 26, 1, 0),
    (27, 'Runway', '跑道', 'Runway', 'Gen-4 series video generation', 'https://runwayml.com', 27, 1, 0),
    (28, 'MiniMaxH3', '海螺', 'MiniMax Hailuo', 'Hailuo H3 video generation', 'https://minimax.io', 28, 1, 0),
    (29, 'HappyHorse', '快乐马', 'Alibaba HappyHorse', 'Fast video generation', NULL, 29, 1, 0),
    (30, 'Ltx', 'LTX', 'Lightricks LTX', 'LTX video generation models', 'https://ltx.io', 30, 1, 0),
    (31, 'GrokImagine', '格罗克想象', 'xAI Grok Imagine', 'Grok Imagine video generation', 'https://x.ai', 31, 1, 0),
    (32, 'Pruna', '普鲁纳', 'Pruna', 'P-Video generation models', 'https://pruna.ai', 32, 1, 0),
    (33, 'GeminiOmniFlash', '双子星全能闪速', 'Google Gemini Omni Flash', 'Gemini Omni Flash video generation', 'https://ai.google.dev', 33, 1, 0),
    (34, 'Seedream', '豆包 Seedream', 'ByteDance Seedream', 'Text-to-image generation', 'https://seed.bytedance.com', 34, 1, 0),
    (35, 'WanImage', '通义万相图片', 'Alibaba Wan Image', 'Wan text-to-image', 'https://alibabacloud.com', 35, 1, 0),
    (36, 'StabilityImage', '稳定智能', 'Stability AI', 'Stable Diffusion image generation', 'https://stability.ai', 36, 1, 0),
    (37, 'Flux', '黑森林实验室', 'Black Forest Labs', 'FLUX image generation', 'https://blackforestlabs.ai', 37, 1, 0),
    (38, 'Imagen', '图像', 'Google Imagen', 'Imagen text-to-image', 'https://ai.google.dev', 38, 1, 0),
    (39, 'DallE', '达利', 'OpenAI DALL·E', 'DALL·E image generation', 'https://openai.com', 39, 1, 0),
    (40, 'QwenTts', '通义语音', 'Alibaba Qwen TTS', 'Qwen-Audio TTS', 'https://alibabacloud.com', 40, 1, 0),
    (41, 'SeedAudio', '豆包音频', 'ByteDance Seed Audio', 'Audio creation model', 'https://seed.bytedance.com', 41, 1, 0),
    (42, 'StepAudio', '阶跃音频', 'StepFun StepAudio', 'TTS, soundscape and music', 'https://stepfun.com', 42, 1, 0),
    (43, 'GeminiTts', '双子星语音', 'Google Gemini TTS', 'Gemini text-to-speech', 'https://ai.google.dev', 43, 1, 0),
    (44, 'ElevenLabs', '十一实验室', 'ElevenLabs', 'Premium TTS', 'https://elevenlabs.io', 44, 1, 0),
    (45, 'Lyria', '里拉', 'Google Lyria', 'Music generation', 'https://deepmind.google/technologies/lyria', 45, 1, 0),
    (46, 'Suno', '苏诺', 'Suno', 'AI music generation', 'https://suno.com', 46, 1, 0),
    (47, 'StableAudio', '稳定音频', 'Stability Audio', 'Stable Audio music generation', 'https://stability.ai', 47, 1, 0) ON DUPLICATE KEY
UPDATE `name_cn` =
VALUES
    (`name_cn`);

-- ------------------------------------------------------------
-- AI models
-- Modality: chat / image / video / audio
-- Prices sourced from public pricing pages where available;
-- rows marked "estimated" in description use industry-typical values.
-- ------------------------------------------------------------
INSERT INTO
    `t_sys_ai_model` (`id`, `vendor_id`, `model_code`, `model_name`, `modality`, `provider_code`, `description`, `sort_order`, `status`, `is_deleted`)
VALUES
    -- Chat models
    (1, 1, 'gpt-4o', 'GPT-4o', 'chat', 'OpenAI', 'OpenAI flagship multimodal chat model', 1, 1, 0),
    (2, 1, 'o1', 'o1', 'chat', 'OpenAI', 'OpenAI reasoning model', 2, 1, 0),
    (3, 2, 'claude-sonnet-4', 'Claude Sonnet 4', 'chat', 'Anthropic', 'Anthropic balanced model', 1, 1, 0),
    (4, 2, 'claude-opus-4', 'Claude Opus 4', 'chat', 'Anthropic', 'Anthropic most capable model', 2, 1, 0),
    (5, 3, 'gemini-2.5-pro', 'Gemini 2.5 Pro', 'chat', 'Google', 'Google multimodal chat model', 1, 1, 0),
    (6, 4, 'deepseek-chat', 'DeepSeek Chat', 'chat', 'DeepSeek', 'DeepSeek general chat model', 1, 1, 0),
    (7, 4, 'deepseek-reasoner', 'DeepSeek Reasoner', 'chat', 'DeepSeek', 'DeepSeek reasoning model', 2, 1, 0),
    (8, 5, 'command-r-plus', 'Command R+', 'chat', 'Cohere', 'Cohere enterprise model', 1, 1, 0),
    (9, 8, 'mistral-large', 'Mistral Large', 'chat', 'Mistral', 'Mistral flagship model', 1, 1, 0),
    (10, 9, 'llama-3.3-70b', 'Llama 3.3 70B', 'chat', 'Groq', 'Groq-hosted Llama model', 1, 1, 0),
    (11, 15, 'qwen-max', 'Qwen Max', 'chat', 'Alibaba', 'Alibaba flagship chat model', 1, 1, 0),
    (12, 17, 'glm-4', 'GLM-4', 'chat', 'Zhipu', 'Zhipu GLM-4 model', 1, 1, 0),
    (13, 18, 'abab6.5', 'abab6.5', 'chat', 'MiniMax', 'MiniMax long-context model', 1, 1, 0),
    (14, 19, 'moonshot-v1-128k', 'Moonshot 128K', 'chat', 'Moonshot', 'Moonshot long-context model', 1, 1, 0),
    -- Video models
    (15, 23, 'seedance-2-0', 'Seedance 2.0', 'video', 'Seedance', 'ByteDance text-to-video', 1, 1, 0),
    (16, 23, 'seedance-2-0-fast', 'Seedance 2.0 Fast', 'video', 'Seedance', 'ByteDance fast video', 2, 1, 0),
    (17, 24, 'wan2.6-i2v', 'Wan 2.6 I2V', 'video', 'Wan', 'Alibaba image-to-video 1080p', 1, 1, 0),
    (18, 24, 'wan2.2-i2v-plus', 'Wan 2.2 I2V Plus', 'video', 'Wan', 'Alibaba image-to-video', 2, 1, 0),
    (19, 25, 'kling-4-0', 'Kling 4.0', 'video', 'Kling', 'Kuaishou video generation', 1, 1, 0),
    (20, 26, 'veo-3-1', 'Veo 3.1', 'video', 'Veo', 'Google video + audio generation', 1, 1, 0),
    (21, 26, 'veo-3-1-fast', 'Veo 3.1 Fast', 'video', 'Veo', 'Google fast video generation', 2, 1, 0),
    (22, 27, 'runway-gen-4-5', 'Runway Gen-4.5', 'video', 'Runway', 'Runway video generation', 1, 1, 0),
    (23, 28, 'minimax-hailuo-2-3', 'MiniMax Hailuo 2.3', 'video', 'MiniMaxH3', 'MiniMax 768p video', 1, 1, 0),
    (24, 29, 'happyhorse-1-0', 'HappyHorse 1.0', 'video', 'HappyHorse', 'Alibaba fast video 720p', 1, 1, 0),
    (25, 30, 'ltx-2-5-fast', 'LTX 2.5 Fast', 'video', 'Ltx', 'Lightricks fast video 720p', 1, 1, 0),
    (26, 31, 'grok-imagine-video-1-5', 'Grok Imagine 1.5', 'video', 'GrokImagine', 'xAI video generation', 1, 1, 0),
    (27, 32, 'p-video-2', 'P-Video 2', 'video', 'Pruna', 'Pruna premium video 720p', 1, 1, 0),
    (28, 33, 'gemini-omni-1-2-flash', 'Gemini Omni 1.2 Flash', 'video', 'GeminiOmniFlash', 'Google 15s video 720p', 1, 1, 0),
    -- Image models
    (29, 34, 'seedream-4-0', 'Seedream 4.0', 'image', 'Seedream', 'ByteDance image generation', 1, 1, 0),
    (30, 35, 'wan-image', 'Wan Image', 'image', 'WanImage', 'Alibaba text-to-image', 1, 1, 0),
    (31, 36, 'sd-3-5-large', 'SD 3.5 Large', 'image', 'StabilityImage', 'Stability image generation (estimated)', 1, 1, 0),
    (32, 37, 'flux-1-1-pro', 'FLUX 1.1 Pro', 'image', 'Flux', 'Black Forest Labs image (estimated)', 1, 1, 0),
    (33, 38, 'imagen-3', 'Imagen 3', 'image', 'Imagen', 'Google image generation (estimated)', 1, 1, 0),
    (34, 39, 'dall-e-3', 'DALL·E 3', 'image', 'DallE', 'OpenAI image generation (estimated)', 1, 1, 0),
    -- Audio models
    (35, 40, 'qwen-tts', 'Qwen TTS', 'audio', 'QwenTts', 'Alibaba TTS', 1, 1, 0),
    (36, 41, 'seed-audio-1-0', 'Seed Audio 1.0', 'audio', 'SeedAudio', 'ByteDance audio creation', 1, 1, 0),
    (37, 42, 'step-audio-3', 'StepAudio 3', 'audio', 'StepAudio', 'StepFun audio generation', 1, 1, 0),
    (38, 43, 'gemini-tts', 'Gemini TTS', 'audio', 'GeminiTts', 'Google TTS', 1, 1, 0),
    (39, 44, 'eleven-v4', 'Eleven v4', 'audio', 'ElevenLabs', 'ElevenLabs TTS (estimated)', 1, 1, 0),
    (40, 45, 'lyria-3-pro', 'Lyria 3 Pro', 'audio', 'Lyria', 'Google music generation (estimated)', 1, 1, 0),
    (41, 46, 'suno-v6', 'Suno v6', 'audio', 'Suno', 'Suno music generation (estimated)', 1, 1, 0),
    (42, 47, 'stable-audio-2-5', 'Stable Audio 2.5', 'audio', 'StableAudio', 'Stability music generation (estimated)', 1, 1, 0) ON DUPLICATE KEY
UPDATE `model_name` =
VALUES
    (`model_name`);

-- ------------------------------------------------------------
-- AI model billing
-- Unit: image = per image, second = per video/audio second, k_token = per 1K tokens
-- Prices in USD where public; estimated values are industry-typical.
-- ------------------------------------------------------------
INSERT INTO
    `t_sys_ai_model_billing` (`id`, `vendor_id`, `model_id`, `billing_unit`, `unit_price_usd`, `currency`, `effective_from`, `status`, `is_deleted`)
VALUES
    -- Chat: per 1K tokens (estimated typical mid-range pricing)
    (1, 1, 1, 'k_token', 0.00500000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (2, 1, 2, 'k_token', 0.01500000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (3, 2, 3, 'k_token', 0.00300000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (4, 2, 4, 'k_token', 0.01500000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (5, 3, 5, 'k_token', 0.00125000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (6, 4, 6, 'k_token', 0.00027000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (7, 4, 7, 'k_token', 0.00055000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (8, 5, 8, 'k_token', 0.00250000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (9, 8, 9, 'k_token', 0.00200000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (10, 9, 10, 'k_token', 0.00059000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (11, 15, 11, 'k_token', 0.00240000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (12, 17, 12, 'k_token', 0.00140000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (13, 18, 13, 'k_token', 0.00100000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (14, 19, 14, 'k_token', 0.00120000, 'USD', '2025-01-01 00:00:00', 1, 0),
    -- Video: per second (USD)
    (15, 23, 15, 'second', 0.09000000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (16, 23, 16, 'second', 0.05000000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (17, 24, 17, 'second', 0.15000000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (18, 24, 18, 'second', 0.08601200, 'USD', '2025-01-01 00:00:00', 1, 0),
    (19, 25, 19, 'second', 0.14700000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (20, 26, 20, 'second', 0.40000000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (21, 26, 21, 'second', 0.10000000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (22, 27, 22, 'second', 0.08000000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (23, 28, 23, 'second', 0.07000000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (24, 29, 24, 'second', 0.12600000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (25, 30, 25, 'second', 0.09000000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (26, 31, 26, 'second', 0.08000000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (27, 32, 27, 'second', 0.02500000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (28, 33, 28, 'second', 0.07000000, 'USD', '2025-01-01 00:00:00', 1, 0),
    -- Image: per image (USD, estimated where not public)
    (29, 34, 29, 'image', 0.04000000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (30, 35, 30, 'image', 0.03000000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (31, 36, 31, 'image', 0.06500000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (32, 37, 32, 'image', 0.04000000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (33, 38, 33, 'image', 0.04000000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (34, 39, 34, 'image', 0.04000000, 'USD', '2025-01-01 00:00:00', 1, 0),
    -- Audio: per second (USD, estimated typical)
    (35, 40, 35, 'second', 0.00010000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (36, 41, 36, 'second', 0.00020000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (37, 42, 37, 'second', 0.00015000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (38, 43, 38, 'second', 0.00010000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (39, 44, 39, 'second', 0.00030000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (40, 45, 40, 'second', 0.00020000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (41, 46, 41, 'second', 0.00025000, 'USD', '2025-01-01 00:00:00', 1, 0),
    (42, 47, 42, 'second', 0.00015000, 'USD', '2025-01-01 00:00:00', 1, 0) ON DUPLICATE KEY
UPDATE `unit_price_usd` =
VALUES
    (`unit_price_usd`);

-- ------------------------------------------------------------
-- AI model credit rate
-- Default 1:1 (1 credit per billing unit) for all models.
-- Adjust individual rows to change credit consumption.
-- ------------------------------------------------------------
INSERT INTO
    `t_sys_ai_model_credit_rate` (`id`, `model_id`, `credit_per_unit`, `effective_from`, `status`, `is_deleted`)
SELECT
    `id`,
    `id`,
    1.00000000,
    '2025-01-01 00:00:00',
    1,
    0
FROM
    `t_sys_ai_model`
WHERE
    `is_deleted` = 0 ON DUPLICATE KEY
UPDATE `credit_per_unit` =
VALUES
    (`credit_per_unit`);

-- ------------------------------------------------------------
-- Fiat currency exchange rates
-- All initialized at 1:1 (1 unit of currency = 1 credit).
-- Adjust `credit_per_unit` to reflect actual business rates.
-- ------------------------------------------------------------
INSERT INTO
    `t_sys_currency_exchange_rate` (`id`, `currency_code`, `currency_name`, `symbol`, `credit_per_unit`, `effective_from`, `status`, `is_deleted`)
VALUES
    (1, 'USD', 'US Dollar', '$', 1.00000000, '2025-01-01 00:00:00', 1, 0),
    (2, 'CNY', 'Chinese Yuan', '¥', 1.00000000, '2025-01-01 00:00:00', 1, 0),
    (3, 'EUR', 'Euro', '€', 1.00000000, '2025-01-01 00:00:00', 1, 0),
    (4, 'GBP', 'British Pound', '£', 1.00000000, '2025-01-01 00:00:00', 1, 0),
    (5, 'JPY', 'Japanese Yen', '¥', 1.00000000, '2025-01-01 00:00:00', 1, 0),
    (6, 'KRW', 'South Korean Won', '₩', 1.00000000, '2025-01-01 00:00:00', 1, 0),
    (7, 'HKD', 'Hong Kong Dollar', 'HK$', 1.00000000, '2025-01-01 00:00:00', 1, 0),
    (8, 'TWD', 'New Taiwan Dollar', 'NT$', 1.00000000, '2025-01-01 00:00:00', 1, 0),
    (9, 'SGD', 'Singapore Dollar', 'S$', 1.00000000, '2025-01-01 00:00:00', 1, 0),
    (10, 'AUD', 'Australian Dollar', 'A$', 1.00000000, '2025-01-01 00:00:00', 1, 0),
    (11, 'CAD', 'Canadian Dollar', 'C$', 1.00000000, '2025-01-01 00:00:00', 1, 0),
    (12, 'CHF', 'Swiss Franc', 'CHF', 1.00000000, '2025-01-01 00:00:00', 1, 0),
    (13, 'INR', 'Indian Rupee', '₹', 1.00000000, '2025-01-01 00:00:00', 1, 0),
    (14, 'BRL', 'Brazilian Real', 'R$', 1.00000000, '2025-01-01 00:00:00', 1, 0),
    (15, 'RUB', 'Russian Ruble', '₽', 1.00000000, '2025-01-01 00:00:00', 1, 0),
    (16, 'AED', 'UAE Dirham', 'د.إ', 1.00000000, '2025-01-01 00:00:00', 1, 0) ON DUPLICATE KEY
UPDATE `credit_per_unit` =
VALUES
    (`credit_per_unit`);