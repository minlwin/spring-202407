'use client'

import { CourseListLocalContextProvider } from "./state/course-list-local-state";
import { CourseSearchLocalContextProvider } from "./state/course-search-local-state";

export default function CourseLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
    return (
        <CourseListLocalContextProvider>
            <CourseSearchLocalContextProvider>
                {children}
            </CourseSearchLocalContextProvider>
        </CourseListLocalContextProvider>
    )
}