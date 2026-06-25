import styles from "./HeroSection.module.scss";

export default function HeroSection() {
  return (
    <section className={styles.section}>
      <div className={styles.container}>
        <p className={styles.kicker}>Pages Router Starter</p>
        <h1 className={styles.title}>nextjs-fe is ready for client-side work.</h1>
        <p className={styles.description}>
          This project starts with the folder structure, SCSS foundation, and
          client-only constraints defined in the repository guide.
        </p>
      </div>
    </section>
  );
}
