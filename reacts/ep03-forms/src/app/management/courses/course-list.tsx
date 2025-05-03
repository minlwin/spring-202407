'use client'

import { Dropdown, ListItemDecorator, Menu, MenuButton, MenuItem, Table } from "@mui/joy";
import { useLocalCourses, useLocalCoursesSetter } from "./state/course-list-local-state";
import { useLocalCourseSearch } from "./state/course-search-local-state";
import { useEffect } from "react";
import { useCourseDispatch, useCourses } from "@/lib/state/course-provider";
import { Course, CourseSearch } from "@/lib/model/course-model";
import { Add, Delete, Edit, MoreVert } from "@mui/icons-material";
import { useSelectedCourseSetter } from "./state/selected-course-local-state";
import { useRouter } from "next/navigation";
import { useSelectedMenuSetter } from "@/lib/state/selected-menu-provider";

export default function CourseList({showEdit} : {showEdit : () => void}) {

    const list = useLocalCourses()
    const setList = useLocalCoursesSetter()

    const globalCourses = useCourses()
    const search = useLocalCourseSearch()
    const courseDispatch = useCourseDispatch()
    const selectedCourseSetter = useSelectedCourseSetter()

    const router = useRouter()
    const setSelectedMenu = useSelectedMenuSetter()

    useEffect(() => {
        const isMatch = (course:Course, search?:CourseSearch) => {
            let result = true
            if(search?.level) {
                result = course.level === search.level
            }

            if(search?.status) {
                result = course.deleted === search.status
            }

            if(search?.keyword) {
                result = course.name.toLocaleLowerCase().startsWith(search?.keyword?.toLocaleLowerCase())
            }

            if(search?.hoursFrom) {
                result = course.hours >= search?.hoursFrom
            }

            if(search?.hoursTo) {
                result = course.hours <= search?.hoursTo
            }

            return result;
        }
        setList(globalCourses.filter(a => isMatch(a, search)))
    }, [search, globalCourses])

    const deleteAction = (id:number) => {
        courseDispatch({type :'Delete', id: id})
    }

    const editAction = (course:Course) => {
        selectedCourseSetter({...course})   
        showEdit()
    }

    const addNewClassAction = (course:Course) => {
        router.push(`/management/classes/edit?id=${course.id}`)
        setSelectedMenu({icon : <Edit />, route: '', title : 'Create Class'})
    }

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
                        <th style={{width : '4rem'}}></th>
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
                        <td style={{width : '4rem', textAlign: 'center'}}>
                            <Dropdown>
                                <MenuButton variant="plain">
                                    <MoreVert />
                                </MenuButton>
                                <Menu placement="bottom-end">
                                    <MenuItem onClick={() => editAction(course)}>
                                        <ListItemDecorator>
                                            <Edit />
                                        </ListItemDecorator>
                                        Edit
                                    </MenuItem>
                                    <MenuItem onClick={() => deleteAction(course.id)}>
                                        <ListItemDecorator>
                                            <Delete />
                                        </ListItemDecorator>
                                        Delete
                                    </MenuItem>
                                    {!course.deleted && 
                                        <MenuItem onClick={() => addNewClassAction(course)}>
                                            <ListItemDecorator>
                                                <Add />
                                            </ListItemDecorator>
                                            Create New Class
                                        </MenuItem>
                                    }
                                </Menu>
                            </Dropdown>
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </>
    )
}