'use client'
import React, { ActionDispatch, createContext, useContext, useReducer } from "react"
import { Course } from "../model/course-model"
import { CourseAction, courseReducer } from "../reducers/course-reducer"

type CourseContextType = {
    courses : Course[]
    dispatch: ActionDispatch<[action: CourseAction]> 
}

const CourseContext = createContext<CourseContextType | undefined>(undefined)

function loadCourse() {

    try {
        const str = localStorage.getItem('app.state.courses')
        if(str) {
            return JSON.parse(str)
        }
    } catch(e) {}

    return []
}

function CourseContextProvider({children} : {children: React.ReactNode}) {
    const [state, dispatch] = useReducer(courseReducer, loadCourse())

    return (
        <CourseContext.Provider value={{courses: state, dispatch: dispatch}}>
            {children}
        </CourseContext.Provider>
    )
}

function useCourseContext() {
    const context = useContext(CourseContext)

    if(!context) {
        throw new Error('Invalid useage of course context.')
    }

    return context
}

function useCourses() {
    return useCourseContext().courses
}

function useCourseDispatch() {
    return useCourseContext().dispatch
}

export {
    CourseContextProvider, useCourses, useCourseDispatch
}