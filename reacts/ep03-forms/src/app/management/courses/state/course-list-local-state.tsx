'use client'

import { Course } from "@/lib/model/course-model"
import React, { createContext, useContext, useState } from "react"

type CourseListLocalContextType = {
    courses: Course[]
    setCourses : (courses : Course[]) => void
}

const CourseListLocalContext = createContext<CourseListLocalContextType | undefined>(undefined)

function CourseListLocalContextProvider({children} : {children : React.ReactNode}) {
    const [courses, setCourses] = useState<Course[]>([])

    return (
        <CourseListLocalContext.Provider value={{courses, setCourses}}>
            {children}
        </CourseListLocalContext.Provider>
    )
}

function useCourseListLocalContext() {
    const context = useContext(CourseListLocalContext)

    if(!context) {
        throw new Error('Invalid usage of Local Course List Context')
    }

    return context
}

function useLocalCourses() {
    return useCourseListLocalContext().courses
}

function useLocalCoursesSetter() {
    return useCourseListLocalContext().setCourses
}

export {
    CourseListLocalContextProvider, 
    useLocalCourses,
    useLocalCoursesSetter
}