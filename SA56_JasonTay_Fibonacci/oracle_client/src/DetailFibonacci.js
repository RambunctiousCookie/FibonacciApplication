export default function DetailFibonacci( {responseObj} ){

    const ListFibonacci = responseObj.fibonacci.map((number, index) => (
        <div key={index} className="item">
            {number}
        </div>
    ));
    const ListSorted = responseObj.sorted.map((number, index) => (
        <div key={index} className="item">
            {number}
        </div>
    ));
    
    return (
        <div>
        {/* Render the data once it's available */}
        {responseObj && (
            <>
            <h2>Fibonacci Array</h2>
            <div className="flex-container">
                {ListFibonacci}
            </div>

            <h2>Sorted Array</h2>
            <div className="flex-container">
                {ListSorted}
            </div>
            </>
        )}
    </div>
    );

}