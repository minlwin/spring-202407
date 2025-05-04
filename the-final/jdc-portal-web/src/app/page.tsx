import HomeAbout from "@/lib/home/home-about";
import OurCourses from "@/lib/home/home-courses";
import HomeCover from "@/lib/home/home-cover";

export default function Home() {
  return (
    <>
      {/* Cover Images */}
      <HomeCover />

      {/* About JDC */}
      <HomeAbout />

      {/* Our Courses */}
      <OurCourses />

      {/* Contacts */}
    </>
  )
}