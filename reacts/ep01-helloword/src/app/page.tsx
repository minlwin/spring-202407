import Profile from "@/components/profile";

export default function Home() {
  return (
    <div className="grid grid-cols-4 gap-4">
      <Profile name="Aung Aung" message="Hello World" />
      <Profile name="Aung Aung" message="Hello World" />
      <Profile name="Aung Aung" message="Hello World" />
      <Profile name="Aung Aung" message="Hello World" />
      <Profile name="Aung Aung" message="Hello World" />
    </div>
  );
}
