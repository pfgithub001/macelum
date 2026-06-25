# NEXTJS_GUIDE.md

## Purpose

This document defines how any AI assistant must work when creating, editing, reviewing, or restructuring a Next.js project that follows this architecture.

This guide is the source of truth for:
- project creation
- folder structure
- routing
- styling
- component placement
- non-visual code placement
- README expectations
- validation steps

Follow this guide unless the user explicitly requests a different architecture.

## Mandatory Rule For AI Assistants

Before making any Next.js-related change, always read this guide.

This includes:
- creating a new Next.js project
- adding pages
- adding sections
- adding components
- editing styling
- changing structure
- adding hooks, services, utils, or types
- reviewing or refactoring code

If the existing repository already uses a different established architecture, inspect the codebase first and preserve the existing project pattern unless the user explicitly asks for a migration.

Do not introduce competing architectural patterns without explicit instruction.

## Core Project Assumptions

Unless the user explicitly requests otherwise, assume the project must follow these rules:

- Use Next.js with the `pages` router
- Use TypeScript
- Use a `src/` directory
- Treat the app as client-side by default
- Use `src/global.scss` as the global stylesheet entrypoint
- Use SCSS as the primary styling system
- Use SCSS Modules for component and section styles
- Use a token-only SCSS foundation in `src/styles/`
- Do not use Tailwind by default
- Do not introduce `app/` router patterns
- Do not introduce server-side features unless explicitly requested

## How To Create A New Next.js Project

When creating a new project, always scaffold a Next.js application that matches this architecture.

### Required Defaults

- Package manager: `pnpm`
- Router: `pages`
- Language: `TypeScript`
- Source directory: `src`
- Linting: `ESLint`
- Styling system: `SCSS`
- Global stylesheet: `src/global.scss`

### Preferred Scaffold Example

Use `pnpm` as the default package manager.

Preferred example:

```bash
pnpm create next-app
```

When using the scaffold flow, always choose the options that produce this result:
- `pages` router
- TypeScript
- `src/` directory
- ESLint
- no Tailwind

The exact CLI prompts or flags may change over time. The required outcome matters more than the exact command syntax.

If a different scaffold path is used, the resulting project must still match this architecture exactly.

### Required Result After Scaffolding

After scaffolding, the project must be normalized to this structure and setup:
- `pages` router only
- client-side only by default
- `src/` directory required
- `src/global.scss` as the global stylesheet entrypoint
- `sass` installed
- SCSS Modules for component and section styles
- token-only SCSS foundation in `src/styles/`

If the scaffold tool generates files, folders, or defaults that conflict with this guide, normalize the project immediately before adding features.

### Client-Side Rule

This architecture is client-side only by default.

Do not introduce:
- API routes
- `getServerSideProps`
- `getStaticProps`
- `getStaticPaths`
- server actions
- Server Components
- `app/` router patterns

Only add server-side features if the user explicitly requests them.

### Required Creation Flow

1. Create the Next.js project
2. Ensure the project uses the `pages` router
3. Ensure the project uses `src/`
4. Install `sass`
5. Create the canonical folder structure
6. Add `src/global.scss`
7. Import `src/global.scss` from `src/pages/_app.tsx`
8. Create the SCSS partials in `src/styles/`
9. Remove generated files or patterns that do not fit this architecture
10. Create or update `README.md`
11. Verify that the project runs correctly

### Canonical `src/` Structure

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

### Required SCSS Foundation

```text
src/styles/
  _colors.scss
  _breakpoints.scss
  _shadows.scss
  _animations.scss
  _typographies.scss
  _spacing.scss
  _z-index.scss
  _mixins.scss
  _functions.scss
  _reset.scss
  _base.scss
```

### Creation Rules

Always:
- use the `pages` router
- use `src/`
- install `sass`
- create `src/global.scss`
- create the canonical folder structure
- create the SCSS foundation files
- update `README.md`

Never:
- introduce the `app/` router by default
- introduce Tailwind by default
- add server-side features unless explicitly requested
- leave scaffold artifacts that conflict with this guide

## README Rules

Whenever creating a new project, always create or update `README.md`.

Project creation is not complete until the README contains accurate setup and run instructions.

### Required README Content

At minimum, the README must include:
- project name
- short project description
- prerequisites
- installation instructions
- development command
- production build command
- production start command
- lint command
- any other important scripts that actually exist in `package.json`
- short project structure summary
- styling approach
- architecture constraints
- environment variable setup, if applicable

### README Writing Rules

Always:
- keep the README short and operational
- document only real commands that exist
- explain how to run the project locally
- explain the main folder structure
- mention important architectural constraints

Never:
- leave placeholder sections
- include inaccurate commands
- omit required setup steps

## Pages Router Rules

Use the `pages` router only.

### Routing Rules

- Route files must live in `src/pages/`
- Route files should stay thin
- Pages should compose sections
- Pages may contain light route-level wiring
- Pages should not contain large blocks of section-level UI markup when that code belongs in a section

### Forbidden Routing Patterns

Do not introduce:
- `app/`
- `layout.tsx`
- `template.tsx`
- Server Components
- server actions
- App Router route handlers

Do not add `getServerSideProps`, `getStaticProps`, or `getStaticPaths` unless the user explicitly requests server-side behavior.

## Page, Section, And Component Responsibilities

This architecture separates route files, full-width page blocks, and reusable UI into distinct layers.

### Core Composition Rule

- `pages/` compose `sections/`
- `sections/` compose `components/`
- `components/` may compose other `components/`

The distinction is based on responsibility, not nesting depth.

### `pages/`

Use `src/pages/` for route entry files only.

A page should:
- represent a route
- assemble the sections needed for that route
- stay relatively thin and readable
- contain only light route-level wiring

A page should not:
- contain large visual implementations that belong in a section
- become a reusable UI layer
- contain unrelated styling or business logic

### `sections/`

Use `src/sections/` for reusable full-width page blocks.

A section should:
- represent a full horizontal slice of a page
- be potentially reusable across pages
- compose reusable components
- own its section-level markup and styling

A section should not:
- be organized by page name by default
- become a small reusable component library
- define routes

Important rule:
- A section fills the page width at the wrapper level.
- Inner content may still use a constrained container for readability.

Typical pattern:

```tsx
<section className={styles.section}>
  <div className={styles.container}>
    ...
  </div>
</section>
```

### `components/`

Use `src/components/` for reusable UI building blocks.

A component should:
- solve a reusable UI problem
- be usable inside sections or inside other components
- accept props when configuration is needed

A component should not:
- take over route composition
- act as a page-level section unless that is truly its purpose
- become a dumping ground for page-specific markup

Examples of components:
- `Button`
- `Card`
- `Modal`
- `ImageBlock`

### Section vs Component Rule

Use `sections/` when the unit is:
- a reusable full-width page slice
- intended to be stacked as part of a page
- larger in page composition scope

Use `components/` when the unit is:
- a reusable UI element
- used inside sections or other components
- not defined by being a page-level full-width slice

### Section Organization Rule

Assume sections are potentially shared from the start.

Default to a flat `sections/` structure:

```text
src/sections/
  HeroSection/
  FeaturesSection/
  ContactSection/
```

If the folder becomes crowded, group by domain or purpose, not by route:

```text
src/sections/
  marketing/
    HeroSection/
    FeaturesSection/
  account/
    ProfileSection/
```

## SCSS Architecture

Use SCSS as the primary styling system for this project structure.

### Styling System Rule

- Default to SCSS
- Use SCSS Modules for component and section styles
- Do not introduce Tailwind unless the user explicitly requests it
- If an existing repo already uses Tailwind, preserve that pattern unless the user asks for a migration
- Do not mix SCSS and Tailwind as parallel primary styling systems

### Global Styling Entry Point

- The single global stylesheet must be `src/global.scss`
- Import `src/global.scss` only from `src/pages/_app.tsx`
- Use `global.scss` for application-wide styles only

### What Belongs In `src/global.scss`

`src/global.scss` should:
- import the shared partials from `src/styles/`
- define the global import order
- apply reset and base styles
- contain app-wide rules such as `html`, `body`, root layout defaults, and global typography defaults

`src/global.scss` should not:
- contain component-specific styling
- become a dumping ground for unrelated styles
- define a utility-class system

### SCSS Foundation Structure

The shared SCSS foundation must live in `src/styles/`.

```text
src/styles/
  _colors.scss
  _breakpoints.scss
  _shadows.scss
  _animations.scss
  _typographies.scss
  _spacing.scss
  _z-index.scss
  _mixins.scss
  _functions.scss
  _reset.scss
  _base.scss
```

### Responsibility Of Each SCSS File

- `_colors.scss`: color tokens only
- `_breakpoints.scss`: responsive breakpoint tokens
- `_shadows.scss`: shadow scale tokens
- `_animations.scss`: keyframes, timing values, motion tokens
- `_typographies.scss`: font families, weights, sizes, line heights
- `_spacing.scss`: spacing scale
- `_z-index.scss`: named layer values
- `_mixins.scss`: reusable SCSS mixins
- `_functions.scss`: reusable SCSS helper functions
- `_reset.scss`: reset or normalization rules
- `_base.scss`: base element styling

### Token-Only Rule

The SCSS foundation is token-only.

That means:
- define variables, scales, mixins, functions, and keyframes
- do not generate utility classes like `.mt-16`, `.text-primary`, `.shadow-lg`
- do not build a custom utility framework from SCSS tokens

Use the tokens in section and component SCSS Modules instead.

### Local Styling Rule

For reusable UI blocks, sections, and components:
- use `*.module.scss`
- colocate the SCSS module with the component or section
- keep styles close to the UI they belong to

Examples:
- `Button.tsx` + `Button.module.scss`
- `HeroSection.tsx` + `HeroSection.module.scss`

### What To Avoid In Styling

- Do not put page-specific styles in `global.scss`
- Do not place component-specific styles in `src/styles/`
- Do not create a global utility class system from SCSS tokens
- Do not introduce Tailwind into this architecture unless explicitly requested
- Do not rely on inline styles except for truly dynamic one-off values

## Hooks, Services, Utils, And Types

Use these folders to separate reusable behavior, external integration logic, pure helpers, and shared TypeScript definitions.

### Core Responsibility Rule

- `hooks/` stores reusable React behavior
- `services/` stores API and external integration logic
- `utils/` stores pure helper functions
- `types/` stores shared TypeScript types and interfaces

Do not mix these responsibilities.

### `hooks/`

Use `src/hooks/` for reusable React logic.

A hook should:
- use React APIs such as `useState`, `useEffect`, `useRef`, or related hooks
- encapsulate reusable client-side behavior
- help keep sections and components cleaner

A hook should not:
- render JSX
- contain styling
- act as an API service layer
- be created for trivial one-off logic without a reason

Examples:
- `useModal`
- `useWindowSize`
- `useDebounce`
- `useLocalStorage`

### `services/`

Use `src/services/` for communication with external systems.

A service should:
- perform API requests
- handle external SDK interactions
- encapsulate request/response logic
- centralize integration concerns

A service should not:
- contain JSX
- contain styling
- depend on component markup
- become a generic helper dump

Examples:
- `apiClient.ts`
- `authService.ts`
- `productService.ts`

### `utils/`

Use `src/utils/` for small, pure helper functions.

A utility should:
- be framework-light or framework-free
- perform formatting, transformation, parsing, or small calculations
- be easy to reuse

A utility should not:
- use React hooks
- manage component state
- own API communication
- include UI concerns

Examples:
- `formatDate`
- `formatCurrency`
- `slugify`
- `truncateText`

### `types/`

Use `src/types/` for shared TypeScript definitions.

A type file should:
- define interfaces, types, enums, or shared model shapes
- support reuse across pages, sections, components, hooks, or services
- stay organized by domain or common concern

A type file should not:
- contain runtime logic
- contain styling
- mix unrelated helper functions into the file

Examples:
- `product.ts`
- `user.ts`
- `common.ts`

## Naming Conventions

Use consistent naming across the project.

### File And Folder Naming

- Components: PascalCase
- Sections: PascalCase
- SCSS Modules: match the component or section name
- Hooks: `useSomething`
- Services: descriptive domain-oriented names
- Types: descriptive domain-oriented names
- Utility files: descriptive helper names

Examples:
- `Button.tsx`
- `Button.module.scss`
- `HeroSection.tsx`
- `HeroSection.module.scss`
- `useWindowSize.ts`
- `productService.ts`
- `formatDate.ts`
- `product.ts`

### Naming Rules

Always:
- prefer clear and descriptive names
- match SCSS module names to their TSX file names
- use domain language where appropriate

Never:
- use vague names like `Helper`, `Stuff`, `CommonThing`
- abbreviate names unless the abbreviation is already standard in the project

## Rules For Creating New Features

Before creating a new feature, inspect the existing project structure and patterns first.

### Creation Rules

Always:
- inspect similar existing code before adding new files
- place code in the narrowest correct layer
- reuse existing components, sections, hooks, services, utils, and types when possible
- prefer small, consistent additions over unnecessary abstractions
- keep pages thin
- keep styling scoped where possible

When creating new UI:
- use `sections/` for reusable full-width page blocks
- use `components/` for reusable UI pieces
- use `*.module.scss` for local styling

When creating new logic:
- use `hooks/` for reusable React behavior
- use `services/` for API and external integrations
- use `utils/` for pure helpers
- use `types/` for shared TypeScript definitions

### Reuse Rule

Before creating something new, check:
- whether a similar section already exists
- whether a reusable component already exists
- whether a hook or service already solves the problem
- whether a type or utility already exists for the same purpose

Prefer extending or reusing existing code over creating parallel patterns.

## Rules For Editing Existing Projects

When editing an existing project, preserve the established structure unless the user explicitly requests a structural change.

### Editing Rules

Always:
- inspect nearby code before editing
- preserve naming and placement conventions
- prefer the smallest correct change
- reuse existing patterns when they already solve the problem

Never:
- move files without a clear reason
- introduce a new architectural pattern casually
- refactor broadly when a local change is enough
- mix competing styling systems in the same feature unless explicitly requested

If the existing code conflicts with this guide:
- follow the established project pattern unless the user asks for alignment or migration

## Validation Checklist

After making changes, validate the work using the commands and checks that exist in the project.

### Required Validation

Always run, when available:
- lint
- typecheck
- tests
- build

Also verify:
- imports are correct
- files were placed in the correct layer
- styles follow the SCSS architecture
- no forbidden Next.js patterns were introduced
- the changed page or feature behaves as intended

### Project Creation Validation

After creating a new project, verify:
- dependencies install successfully
- the dev server command works
- the build command works
- the lint command works
- `src/pages/_app.tsx` imports `src/global.scss`
- the folder structure matches this guide
- `README.md` contains real setup instructions

## Forbidden Patterns

Do not introduce the following unless the user explicitly requests them:

- `app/` router
- Server Components
- server actions
- `layout.tsx`
- App Router route handlers
- `getServerSideProps`
- `getStaticProps`
- `getStaticPaths`
- API routes
- Tailwind in a SCSS-based project
- multiple competing primary styling systems
- SCSS-generated utility class frameworks
- page-based section folder organization by default
- unnecessary architectural layers such as `views/` unless complexity clearly justifies them

## AI Response Format

When finishing work, the assistant should summarize:

- what changed
- which files were added or edited
- why the chosen placement was correct
- what validation was run
- any assumptions, limitations, or follow-up notes

Keep the summary concise, factual, and tied to the actual changes.

## Final Rule

When in doubt:
- inspect the existing code first
- choose the smallest correct change
- place code in the narrowest correct layer
- preserve consistency over novelty
