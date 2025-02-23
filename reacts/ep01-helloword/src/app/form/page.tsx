import FormGroup from "@/components/form-group";
import PageTitle from "@/components/page-title";
import { TextInput } from "flowbite-react";

export default function UsingForm() {
    return (
        <>
            <PageTitle value="Using Form" />

            <form className="grid grid-cols-3 gap-4">
                {/* Name */}
                <FormGroup label="Name">
                    <TextInput />
                </FormGroup>

                {/* Phone */}
                <FormGroup label="Phone">
                    <TextInput />
                </FormGroup>

                {/* Email */}
                <FormGroup label="Email">
                    <TextInput />
                </FormGroup>
            </form>
        </>
    )
}