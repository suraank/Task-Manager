import * as React from 'react';
import Box from '@mui/material/Box';
import { Grid, Typography } from '@mui/material';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import EditIcon from '@mui/icons-material/Edit';
import IconButton from '@mui/material/IconButton';
import { Link } from 'react-router-dom';
import Paper from '@mui/material/Paper';


export default function TaskList() {

    const [taskList, setTaskList] = React.useState([]);

    React.useEffect(() => {
        fetch('http://localhost:8080/api/getTasks', {
            method: 'get',
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            },
        })
            .then(resp => resp.json())
            .then(resp => {
                console.log(resp)
                setTaskList(resp)
            })
            .catch((error) => {
                console.log(error);
            });
    }, [])

    const concatenateStrings = (a, b) => a + b;
    return (
        <Grid container
            spacing={0}
            direction="column"
            alignItems="center"

            sx={{ minHeight: '100vh', marginTop:'5%' }}>
            <Typography variant="h5" >My Tasks</Typography>
            {
                taskList.length === 0 ? (<Paper elevation={3}>
                    No tasks yet
                </Paper>) : ''
            }

            <Box sx={{ width: 500, height: 500, display: 'flex', justifyContent: 'center' }}>
                <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper', alignItems: "center" }}>
                    {taskList.map((value) => (
                        <ListItem
                            component={Link} to={concatenateStrings("/taskDetails/", value.id)}
                            key={value}
                            secondaryAction={
                                <IconButton aria-label="comment" component={Link} to={concatenateStrings("/tasks/", value.id)}>
                                    <EditIcon />
                                </IconButton>
                            }
                        >
                            <Box component="section" style={{ width: '90%' }} sx={{ borderBottom: 1 }}>
                                <Typography variant="h6" gutterBottom>{value.title}</Typography>
                            </Box>
                        </ListItem>
                    ))}
                </List>
            </Box>
        </Grid>
    );
}