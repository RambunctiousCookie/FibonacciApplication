import {useEffect,useState, useRef } from "react";
import axios from 'axios';
import DetailValidation from "./DetailValidation";
import DetailErrors from "./DetailErrors";
import DetailFibonacci from "./DetailFibonacci";

export default function UserInputElements(){
    const [responseObj, updateResponseObj] = useState(null);
    const [errors, setErrors] = useState(null);
    const [valid, setValid] = useState(false);     //start with false so it does not post off the bat
    const [inputValue, updateInputValue] = useState(null);
    const userInput = useRef(null);
    
    const apiUrl = "http://localhost:8000/api/fibonacci/post";

    useEffect(() => {
        if (valid) {

            console.log("Current input: (" + userInput.current.value+") Submitting data to server for fibonacci retrieval! (Use POST for Axios)");

            const data = {
                elements: userInput.current.value
            };
    
            axios
                .post(apiUrl, data)
                .then(response=>{
                    updateResponseObj(response.data);
                    console.log("Success!");
                    //console.log(responseObj);
                })
                .catch(e=> {
                    console.log(e);
                    if (e.response) {
                        const error = e.response.data;
                        // Handle validation errors (errors is the Map<String, String>)
                        console.log(error);
                        setErrors(error);
                    }
                })
          }
      }, [valid,inputValue]);     //update if validity or inputValue changes


    function handleRetrieveClick(e) {
        e.preventDefault();

        const value = userInput.current.value;
        updateInputValue(value);
    
        // Check if the input value is a number between 1 and 100
        const validity = /^\d+$/.test(value) && parseInt(value) >= 1 && parseInt(value) <= 100;
        setValid(validity);

        //useEffect is automatically called when validity changes
    }

    function showRetrieved(){
        if(!valid){
            return(
                < DetailValidation />
            );
        }
        else if (errors) {
            return (
                < DetailErrors error = {errors} />
            );
        }
        else if(responseObj) {
            return(
                < DetailFibonacci responseObj = {responseObj} />
            );
        }
        else{
            return (
                <></>
            );
        }
    }

    return (
        <div>
            <div className="mb-3 mt-3">
                <h2 className='align-text-center text-secondary'>Enter the desired number of Fibonacci elements:</h2> <br/>
                <form>
                    <div className="col-auto">
                        <input type='text' className="form-control" ref={userInput}/>
                        <button type="submit" className="btn btn-secondary mt-3 mb-3" onClick ={handleRetrieveClick}>Submit</button><br/>
                        <br/>
                        {showRetrieved()}
                    </div>
                </form>

            </div>
        </div>
    )
}
