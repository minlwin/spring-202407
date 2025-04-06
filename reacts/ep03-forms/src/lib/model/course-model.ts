export const LEVELS = ["Basic", "Intermediate", "Advance"]

export type CourseSearch = {
    level?: string,
    status?: boolean,
    hoursFrom?: number,
    hoursTo?: number,
    keyword?: string 
}

export type CourseForm = {
    name: string,
    level: string,
    hours: number,
    deleted: boolean,
    description?: string
}

export type Course = CourseForm & {
    id: number,
    createdAt: Date,
    updatedAt: Date
}