'use client'

import { Table } from "@mui/joy";
import { useLocalCourses, useLocalCoursesSetter } from "./state/course-list-local-state";
import { useLocalCourseSearch } from "./state/course-search-local-state";
import { useEffect } from "react";
import { useCourses } from "@/lib/state/course-provider";

export default function CourseList() {

    const list = useLocalCourses()
    const globalCourses = useCourses()
    const setList = useLocalCoursesSetter()
    const search = useLocalCourseSearch()

    useEffect(() => {
        setList([...globalCourses])
    }, [search, setList, globalCourses])

    return (
        <>
            <Table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Level</th>
                        <th>Hours</th>
                        <th>Status</th>
                        <th>Created At</th>
                        <th>Updated At</th>
                    </tr>
                </thead>

                <tbody>
                {list.map(course => (
                    <tr key={course.id}>
                        <td>{course.id}</td>
                        <td>{course.name}</td>
                        <td>{course.level}</td>
                        <td>{course.hours}</td>
                        <td>{course.deleted ? 'Deleted' : 'Active'}</td>
                        <td>{course.createdAt.toLocaleString()}</td>
                        <td>{course.updatedAt.toLocaleString()}</td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </>
    )
}