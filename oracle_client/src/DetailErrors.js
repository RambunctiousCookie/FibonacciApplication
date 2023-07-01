export default function DetailErrors( {error} ){
    return (
        <div>
            <div>Encountered error code {error.status}- please try again!</div>
        </div>
    );

}