'use client'

import { Button } from '@/components/ui/button'
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from '@/components/ui/form'
import { Input } from '@/components/ui/input'
import { zodResolver } from '@hookform/resolvers/zod'
import { LogIn } from 'lucide-react'
import { useForm } from 'react-hook-form'
import {z} from 'zod'

const signInFormSchema = z.object({
    username: z.string()
        .nonempty({message : "Please enter email."})
        .email({message : "Please enter correct email."}),
    password: z.string()
        .nonempty({message : "Please enter password."})
})

export default function SignIn() {

    return (
        <main className='grid grid-cols-3 gap-5 h-full'>
            <div className='flex items-center justify-end me-5'>
                <div>
                    <img src="/logo/logo2017.png" width={200} />
                </div>
            </div>

            <div className='h-full flex items-center'>
                <SignInForm />
            </div>
        </main>
    )
}

function SignInForm() {
    const form = useForm<z.infer<typeof signInFormSchema>>({
        resolver : zodResolver(signInFormSchema),
        defaultValues: {username : '', password : ''}
    })

    const signIn = (signInForm : z.infer<typeof signInFormSchema>) => {
        console.log(signInForm)
    }

    return (
        <div className='w-full'>
            <h1 className='text-2xl'>Sign In</h1>
            <p className='mb-4 text-primary'>Welcome to JDC Portal</p>
            <Form {...form}>
                <form onSubmit={form.handleSubmit(signIn)}>
                    <FormField control={form.control} name='username' render={({field}) => (
                        <FormItem className='mb-3'>
                            <FormLabel>Email</FormLabel>
                            <FormControl>
                                <Input placeholder='Enter Email' {...field} />
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )} />

                    <FormField control={form.control} name='password' render={({field}) => (
                        <FormItem className='mb-3'>
                            <FormLabel>Password</FormLabel>
                            <FormControl>
                                <Input type='password' placeholder='Enter Password' {...field} />
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )} />

                    <Button variant={'outline'}>
                        <LogIn />
                        Sign In
                    </Button>
                </form>
            </Form>
        </div>
    )    
}