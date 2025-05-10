import HomeAbout from "@/lib/home/home-about";
import HomeContact from "@/lib/home/home-contact";
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
      <HomeContact />
    </>
  )
}