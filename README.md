<h1 align="center">
    Basenest
</h1>
A universal multi-tenant SaaS platform framework, a foundational system that supports a variety of desktop and cloud-based applications.

## Environment Variables

The application reads the following environment variables. If an
environment variable is not set, the default value from
`application.properties` is used.

| Variable             | Description                                                                                                        | Default                                     |
| -------------------- | ------------------------------------------------------------------------------------------------------------------ | ------------------------------------------- |
| `JWT_SECRET`         | Secret used to sign and verify JWT tokens. Must be at least 32 bytes for HS256. **Always set this in production.** | `dev-secret-change-me-dev-secret-change-me` |
| `JWT_EXPIRE_DAYS`    | Token validity in days.                                                                                            | `7`                                         |
| `LOGIN_MAX_FAILED`   | Max failed login attempts before the account is temporarily locked.                                                | `5`                                         |
| `LOGIN_LOCK_MINUTES` | Lockout duration in minutes after too many failed attempts.                                                        | `15`                                        |
