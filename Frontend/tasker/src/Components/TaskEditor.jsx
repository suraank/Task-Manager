import React, { useEffect, useState } from 'react'
import { Col, Button, Row, Container, Card, Form , Alert} from 'react-bootstrap'
import { useParams } from 'react-router-dom'
import Navbar from './Navbar'
import Snackbar from '@mui/material/Snackbar';

export default function TaskEditor() {

    const [title, setTitle] = useState("")
    const [description, setDescription] = useState("")
    const [priority, setPriority] = useState("")
    const [message, setMessage] = useState("")
    const [open, setOpen] = React.useState(false);
    const { taskId } = useParams();

    const handleClose = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }

        setOpen(false);
    };

    useEffect(() => {
        console.log(taskId)
        fetch('http://localhost:8080/api/getTaskById/' + taskId, {
            method: 'get',
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            },
        })
            .then(resp => resp.json())
            .then(resp => {
                console.log(resp)
                setTitle(resp.title)
                setDescription(resp.description)
                setPriority(resp.priority)
            })
            .catch((error) => {
                console.log(error);
            });
    }, [taskId])

    const submitTask = () => {
        let data = {
            "title": title,
            "description": description,
            "priority": priority
        };

        if (taskId) data['id'] = taskId;

        fetch('http://localhost:8080/api/createTask', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            },
            body: JSON.stringify(data)
        })
            .then(resp => {
                setOpen(true)
                setMessage("Task has been saved successfully")
            })
            .catch((error) => {
                console.log(error);
            });

        setDescription("")
        setTitle("")
        setPriority("")
    }

    let vertical = "top";
    let horizontal = "center";
    return (
        <React.Fragment>
            <Navbar />
            <Container>
                <Row className="vh-100 d-flex justify-content-center align-items-center">
                    <Col md={10} lg={8} xs={12}>
                        <div className="border-3 border-primary border"></div>
                        <Card className="shadow">
                            <Card.Body>
                                <div className="mb-3 mt-4">
                                    <h2 className="fw-bold text-uppercase mb-2">Task Form</h2>
                                    <Form onSubmit={event => event.preventDefault()}>

                                        <Row className="mb-3">
                                            <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                                                <Form.Label className="text-center">Title</Form.Label>
                                                <Form.Control as="textarea" placeholder="Enter task title" name="title" label='Task title' onChange={e => setTitle(e.target.value)} value={title} />
                                            </Form.Group>
                                        </Row>
                                        <Row className="mb-3">
                                            <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                                                <Form.Label className="text-center">Description</Form.Label>
                                                <Form.Control as="textarea" name="description" placeholder='Type Description'
                                                    onChange={e => setDescription(e.target.value)} value={description} />
                                            </Form.Group>
                                        </Row>
                                        <Row className="mb-3">
                                            <Form.Select id="id" aria-label="Default select example" label="Task Priority"
                                                onChange={e => setPriority(e.target.value)}
                                                value={priority}>
                                                <option>Select the priority</option>
                                                <option value="low">Low</option>
                                                <option value="medium">Medium</option>
                                                <option value="high">High</option>
                                            </Form.Select>
                                        </Row>
                                        <Row className="mb-3">
                                            <Button variant="primary" type="submit" onClick={() => submitTask()}>
                                                Save
                                            </Button>
                                        </Row>

                                    </Form>
                                </div>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
            </Container>
            <Snackbar
                open={open}
                autoHideDuration={6000}
                onClose={handleClose}
                anchorOrigin={{ vertical, horizontal }}
                key="topcenter"
                style={{ width: 300 }}
            >
                <Alert onClose={handleClose} severity="success" sx={{ width: '100%' }}>
                    {message}
                </Alert>
            </Snackbar>
        </React.Fragment>
    )
}