import { Course } from "@/lib/model/course-model"
import React, { createContext, useContext, useState } from "react"

type SelectedCourseContextType = {
    selectedCourse?: Course
    setSelectedCourse: (course?:Course) => void
}

const SelectedCourseContext = createContext<SelectedCourseContextType | undefined>(undefined)

function SelectedCourseContextProvider({children} : {children: React.ReactNode}) {
    const [selectedCourse, setSelectedCourse] = useState<Course | undefined>()
    return (
        <SelectedCourseContext.Provider value={{selectedCourse : selectedCourse, setSelectedCourse : setSelectedCourse}}>
            {children}
        </SelectedCourseContext.Provider>        
    )
}

function useSelectedCourseContext() {
    const context = useContext(SelectedCourseContext)

    if(!context) {
        throw new Error('Invalid usage of Selected Course Context')
    }
    return context
}

function useSelectedCourse() {
    return useSelectedCourseContext().selectedCourse
}

function useSelectedCourseSetter() {
    return useSelectedCourseContext().setSelectedCourse
}

export {
    SelectedCourseContextProvider,
    useSelectedCourse,
    useSelectedCourseSetter
}