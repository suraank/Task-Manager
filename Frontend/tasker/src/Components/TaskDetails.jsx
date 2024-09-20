import * as React from 'react';
import { Typography, Grid } from '@mui/material';
import { useParams } from 'react-router-dom';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Navbar from './Navbar';

export default function TaskDetails() {

    const [task, setTask] = React.useState([]);
    const { id } = useParams();

    React.useEffect(() => {
        fetch('http://localhost:8080/api/getTaskById/' + id, {
            method: 'get',
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            },
        })
            .then(resp => resp.json())
            .then(resp => {
                console.log(resp)
                setTask(resp)
            })
            .catch((error) => {
                console.log(error);
            });
    }, [])

    return (
        <div>
            <Navbar />
            <Grid container
                spacing={0}
                direction="column"
                alignItems="center"

                sx={{ minHeight: '100vh' }}>
                <Typography variant="h5" sx={{ marginBottom: 17 }}>Task Details</Typography>
                <Card sx={{ width: '50%' }}>

                    <CardContent>
                        <Typography gutterBottom variant="h5" component="div">
                            {task.title}
                        </Typography>
                        <Typography variant="body2" sx={{ color: 'text.secondary' }}>
                            {task.description}
                        </Typography>
                        <Typography variant="subtitle1" >
                            Priority - {task.priority}
                        </Typography>
                    </CardContent>

                </Card>
            </Grid>
        </div>

    );
}