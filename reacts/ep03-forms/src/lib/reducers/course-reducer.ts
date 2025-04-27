'use client'
import { Course, CourseForm, CourseSearch } from "../model/course-model"

export type CourseAction = {
    type: 'Create' | 'Update' | 'Delete'
    id?: number
    form? : CourseForm
    search? : CourseSearch
}

const ID_KEY = 'com.jdc.demo.course.id'

let nextId = 0

try {
    const storageCourseId = localStorage.getItem(ID_KEY)
    if(storageCourseId) {
        nextId = Number.parseInt(storageCourseId)
    }
} catch(e) {

}

export function courseReducer(state:Course[], action:CourseAction) {
    let newState = state

    switch(action.type) {
    case 'Create':
        newState = [...state, {
            id: ++nextId,
            ...action.form!,
            createdAt: new Date,
            updatedAt: new Date
        }]
        localStorage.setItem(ID_KEY, nextId.toString())
        break;
    case 'Update':
        newState = update(action.id!, action.form!, state)
        break;
    case 'Delete':
        newState = [...state.filter(a => a.id != action.id)]
        break;
    }
    return newState;
}

function update(id:number, form:CourseForm, state:Course[]): Course[]{
    
    let index = -1;
    for(let i = 0; i < state.length; i++) {
        if(id === state[i].id) {
            index = -1;
        }
    }

    if(index >= 0) {
        let oldCourse = state[index]
        let updatedCourse:Course = {
            ...form, 
            id: id, 
            createdAt : oldCourse.createdAt,
            updatedAt: new Date}

        state[index] = updatedCourse
    }
    return [...state]
}