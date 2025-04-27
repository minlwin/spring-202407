'use client'

import { CourseSearch } from "@/lib/model/course-model"
import { createContext, useContext, useState } from "react"

type CourseSearchLocalContextType = {
    search?: CourseSearch
    setSearch : (search?: CourseSearch) => void
}

const CourseSearchLocalContext = createContext<CourseSearchLocalContextType | undefined>(undefined)

function CourseSearchLocalContextProvider({children} : {children : React.ReactNode}) {
    const [search, setSearch] = useState<CourseSearch>()
    return (
        <CourseSearchLocalContext.Provider value={{search: search, setSearch : setSearch}}>
            {children}
        </CourseSearchLocalContext.Provider>
    )
}

function useCourseSearchContext() {
    const context = useContext(CourseSearchLocalContext)

    if(!context) {
        throw new Error('Invalid context usage of Local Course Search')
    }

    return context
}

function useLocalCourseSearch() {
    return useCourseSearchContext().search
}

function useLocalCourseSearchSetter() {
    return useCourseSearchContext().setSearch
}

export {
    CourseSearchLocalContextProvider,
    useLocalCourseSearch,
    useLocalCourseSearchSetter
}