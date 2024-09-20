import './App.css';
import Dashboard from './Components/Dashboard';
import { BrowserRouter, Route } from 'react-router-dom'
import { Routes } from 'react-router-dom'
import TaskEditor from './Components/TaskEditor';
import TaskList from './Components/TaskList';
import TaskDetails from './Components/TaskDetails';



function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Dashboard />} />
        <Route exact path='/tasks/:taskId' element={<TaskEditor />} />
        <Route exact path='/addTask' element={<TaskEditor />} />
        <Route path='/taskList' element={<TaskList />} />
        <Route path='/taskDetails/:id' element={<TaskDetails />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;