'use client'
import { CourseSearch, LEVELS } from "@/lib/model/course-model";
import { FilterAltOutlined, Search } from "@mui/icons-material";
import { Box, Button, FormControl, FormLabel, Input, Option, Select } from "@mui/joy";
import { Controller, SubmitHandler, useForm } from "react-hook-form";

export default function CourseSearchComponent() {

    const {control, register, handleSubmit} = useForm<CourseSearch>()
    
    const onSubmit:SubmitHandler<CourseSearch> = (form) => {
        console.log(form)
    }

    return (
        <section className="p-8">
            <h3 className="mb-4">
                <FilterAltOutlined /> Search Filters
            </h3>

            <form onSubmit={handleSubmit(onSubmit)}>
                <FormControl sx={{marginBottom:2}}>
                    <FormLabel>Level</FormLabel>
                    <Controller name="level" control={control} render={({field}) => (
                        <Select placeholder="Search Level" value={field.value ?? null} onChange={(_, newValue) => field.onChange(newValue)}>
                            <Option value="">Search All</Option>
                            {LEVELS.map(level => 
                                <Option key={level} value={level}>{level}</Option>
                            )}
                        </Select>
                    )} />
                </FormControl>
                <FormControl sx={{marginBottom:2}}>
                    <FormLabel>Status</FormLabel>
                    <Controller name="status" control={control} render={({field}) => (
                        <Select value={field.value ?? null} onChange={(_, newValue) => field.onChange(newValue)} placeholder="Search Status">
                            <Option value="">Search All</Option>
                            <Option value={false}>Active</Option>
                            <Option value={true}>Deleted</Option>
                        </Select>
                    )} />
                </FormControl>

                <Box sx={{marginBottom: 2}}>
                    <FormLabel sx={{marginBottom : 0.8}}>Hours</FormLabel>
                    <Box sx={{
                        display: 'flex',
                        gap: 2
                    }}>
                        <Input {...register('hoursFrom')} type="number" placeholder="From" sx={{flex: 1}} />
                        <Input {...register('hoursTo')} type="number" placeholder="To" sx={{flex: 1}} />
                    </Box>
                </Box>

                <FormControl sx={{marginBottom : 3}}>
                    <FormLabel>Keyword</FormLabel>
                    <Input {...register('keyword')} placeholder="Search Keyword" />
                </FormControl>

                <Button type="submit" startDecorator={<Search />} variant="outlined" fullWidth={true}>Search</Button>
            </form>
        </section>
    )
}