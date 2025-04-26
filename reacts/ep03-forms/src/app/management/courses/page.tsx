'use client'
import SearchControl from "@/lib/ui/components/search-control";
import { Drawer } from "@mui/joy";
import { useState } from "react";
import CourseEdit from "./course-edit";
import CourseList from "./course-list";
import CourseSearchComponent from "./course-search";

export default function CoursesManagement() {

    const [openEdit, setOpenEdit] = useState(false)
    const [openFilter, setOpenFilter] = useState(false)

    return (
        <>
            <nav className="mb-4">
                <SearchControl onAdd={() => setOpenEdit(true)} onFilter={() => setOpenFilter(true)} />
            </nav>

            <Drawer anchor="right" open={openEdit} onClose={() => setOpenEdit(false)}>
                <CourseEdit />
            </Drawer>

            <Drawer anchor="right" open={openFilter} onClose={() => setOpenFilter(false)}>
                <CourseSearchComponent />
            </Drawer>

            <CourseList />
        </>
    )
}