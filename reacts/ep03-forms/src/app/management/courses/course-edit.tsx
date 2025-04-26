import { CourseForm, LEVELS } from "@/lib/model/course-model";
import { Edit, Save } from "@mui/icons-material";
import { Button, Checkbox, FormControl, FormLabel, Input, Option, Select, Textarea } from "@mui/joy";
import { Controller, useForm, Validate } from "react-hook-form";

export default function CourseEdit() {

    const {control, register, handleSubmit, formState: {errors}} = useForm<CourseForm>()

    const onSubmit = (form:CourseForm) => {
        console.log(form)
    }
    return (
        <section className="p-8">
            <h3 className="mb-4 flex items-center">
                <Edit /> Create Course
            </h3>

            <form onSubmit={handleSubmit(onSubmit)}>
                <FormControl className="mb-3">
                    <FormLabel>Level</FormLabel>
                    <Controller name="level" control={control} render={({field}) => (
                        <Select placeholder="Search Level" value={field.value ?? 'Basic'} onChange={(_, newValue) => field.onChange(newValue)}>
                            {LEVELS.map(level => 
                                <Option key={level} value={level}>{level}</Option>
                            )}
                        </Select>
                    )} rules={{required: 'Please select level.'}} />
                </FormControl>

                <FormControl className="mb-3">
                    <FormLabel>Name</FormLabel>
                    <Input {...register('name', {required:'Please enter course name'})} placeholder="Enter Course Name" />
                    {errors.name && 
                        <span>{errors.name?.message}</span>
                    }
                </FormControl>

                <FormControl className="mb-3">
                    <FormLabel>Hours</FormLabel>
                    <Input {...register('hours', {min:{value: 10, message: 'Minum Hours should be 10.'}, max: {value: 200, message: "Maximum hours should be 200."}})} placeholder="Enter Course Hours" type="number" />
                    {errors.hours &&
                        <span>{errors.hours?.message}</span>
                    }
                </FormControl>

                <FormControl className="mb-3">
                    <FormLabel>Description</FormLabel>
                    <Textarea {...register('description')} placeholder="Enter Description" minRows={3}></Textarea>
                </FormControl>
                
                <Checkbox {...register('deleted')} label="Deleted" />

                <div className="mt-4">
                    <Button type="submit" fullWidth>
                        <Save/> Save
                    </Button>
                </div>
            </form>
        </section>
    )
}