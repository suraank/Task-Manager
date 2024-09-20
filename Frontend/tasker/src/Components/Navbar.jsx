import React from 'react';
import { Button } from '@mui/material';
import { Link } from 'react-router-dom';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';

function Navbar() {

    return (
        <Box sx={{ flexGrow: 1 }}>

            <AppBar position="static">
                <Toolbar >

                    <Typography variant="h5" component="a" sx={{
                        flexGrow: 1,
                        mr: 2,
                        display: { xs: 'none', md: 'flex' },
                        fontFamily: 'monospace',
                        fontWeight: 700,
                        letterSpacing: '.3rem',
                        color: 'inherit',
                        textDecoration: 'none'
                    }} href="/">
                        Task App
                    </Typography>
                    <Button color="inherit" component={Link} to="/addTask">
                        Add Task
                    </Button>
                </Toolbar>
            </AppBar>
        </Box>

    );
}

export default Navbar;