'use client'

import { Menu } from "@mui/icons-material";
import "./globals.css";
import { Drawer, Typography } from "@mui/joy";
import React from "react";
import ApplicationProvider from "@/lib/state/application-provider";
import { useSideBarState, useSideBarStateSetter } from "@/lib/state/sidebar-state";
import SideBar from "@/lib/ui/navs/sidebar";

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <ApplicationProvider>
        <MainLayout>
          {children}
        </MainLayout>
      </ApplicationProvider>
    </html>
  );
}

function MainLayout({children} : {children:React.ReactNode}) {
  const drawerState = useSideBarState()  
  const setDrawerState = useSideBarStateSetter()

  return (
    <body>
      <section className="flex items-center px-8 py-4">
        <span className="cursor-pointer me-2" onClick={() => setDrawerState(true)}>
          <Menu/>
        </span>
        <Typography level="h4">JDC PORTAL</Typography>
      </section>
      <Drawer size="sm" open={drawerState} onClose={() => setDrawerState(false)}>
        <SideBar></SideBar>
      </Drawer>
      {children}
    </body>
  )
}
