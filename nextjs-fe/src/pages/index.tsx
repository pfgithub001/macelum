import Head from "next/head";
import HeroSection from "@/sections/HeroSection/HeroSection";

export default function Home() {
  return (
    <>
      <Head>
        <title>nextjs-fe</title>
        <meta
          name="description"
          content="Client-side Next.js starter aligned with the repository guide."
        />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <main>
        <HeroSection />
      </main>
    </>
  );
}
