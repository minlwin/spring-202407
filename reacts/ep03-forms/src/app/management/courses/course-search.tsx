import { CourseSearch, LEVELS } from "@/lib/model/course-model";
import { FilterAltOutlined, Search } from "@mui/icons-material";
import { Box, Button, FormControl, formControlClasses, FormLabel, Input, Option, Select } from "@mui/joy";
import { Controller, SubmitHandler, useForm, Validate } from "react-hook-form";

export default function CourseSearchComponent() {

    const {control, register, handleSubmit, formState: {errors}} = useForm<CourseSearch>()
    
    const onSubmit:SubmitHandler<CourseSearch> = (form) => {
        console.log(form)
    }

    const fromAndToValidation:Validate<number | undefined, CourseSearch> = (_, formValue) => {
        if(formValue.hoursFrom && formValue.hoursTo) {
            if(Number.parseInt(formValue.hoursFrom.toString()) > Number.parseInt(formValue.hoursTo.toString())) {
                return "Hour from must be less than hour to."
            } 
        }
        return undefined
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
                        <Input {...register('hoursFrom', {validate: fromAndToValidation})} type="number" placeholder="From" sx={{flex: 1}} />
                        <Input {...register('hoursTo', {validate: fromAndToValidation})} type="number" placeholder="To" sx={{flex: 1}} />
                    </Box>
                    {(errors.hoursFrom || errors.hoursTo) &&
                        <span>{errors.hoursFrom?.message ?? errors.hoursTo?.message}</span>
                    }
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