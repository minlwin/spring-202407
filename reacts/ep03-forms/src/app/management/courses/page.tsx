'use client'
import SearchControl from "@/lib/ui/components/search-control";
import { Drawer } from "@mui/joy";
import { useState } from "react";
import CourseEdit from "./course-edit";
import CourseList from "./course-list";
import CourseSearchComponent from "./course-search";
import { useSelectedCourseSetter } from "./state/selected-course-local-state";

export default function CoursesManagement() {

    const [openEdit, setOpenEdit] = useState(false)
    const [openFilter, setOpenFilter] = useState(false)
    const setSelectedCourse = useSelectedCourseSetter()

    const addNewAction = () => {
        setSelectedCourse()
        setOpenEdit(true)
    }

    return (
        <>
            <nav className="mb-4">
                <SearchControl onAdd={addNewAction} onFilter={() => setOpenFilter(true)} />
            </nav>

            <Drawer anchor="right" open={openEdit} onClose={() => setOpenEdit(false)}>
                <CourseEdit setCloseForm={() => setOpenEdit(false)} />
            </Drawer>

            <Drawer anchor="right" open={openFilter} onClose={() => setOpenFilter(false)}>
                <CourseSearchComponent setCloseSearch={() => setOpenFilter(false)} />
            </Drawer>

            <CourseList showEdit={() => setOpenEdit(true)} />
        </>
    )
}