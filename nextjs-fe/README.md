# nextjs-fe

Client-side Next.js starter aligned with `../NEXTJS_GUIDE.md`.

## Tech Stack

- Next.js with the `pages` router
- TypeScript
- SCSS and SCSS Modules
- pnpm

## Prerequisites

- Node.js
- pnpm

## Installation

```bash
pnpm install
```

## Available Scripts

```bash
pnpm dev
pnpm build
pnpm start
pnpm lint
```

## Run Locally

```bash
pnpm dev
```

Open `http://localhost:3000` in the browser.

## Project Structure

```text
src/
  pages/
  sections/
  components/
  hooks/
  services/
  utils/
  types/
  styles/
  global.scss
```

## Styling Approach

- `src/global.scss` is the global stylesheet entrypoint
- `src/styles/` contains shared token partials
- `*.module.scss` is used for section and component styling

## Architecture Constraints

- `pages` router only
- client-side only
- no API routes
- no App Router patterns
- no Tailwind by default
