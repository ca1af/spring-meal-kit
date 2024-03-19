document.addEventListener('DOMContentLoaded', function() {
    function fetchMovies() {
        fetch('/api/movies')
            .then(response => response.json())
            .then(movies => {
                const movieListElement = document.getElementById('movies');
                movies.forEach(movie => {
                    const movieElement = document.createElement('div');
                    movieElement.classList.add("col-md-4", "mb-3");
                    movieElement.innerHTML = `
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${movie.title}</h5>
                            ${generateStars(movie.star)}
                            <p class="card-text">${movie.myReview}</p>
                            <button onclick="location.href='edit.html?id=${movie.id}'" class="btn btn-primary">수정하기</button>
                        </div>
                    </div>`;
                    movieListElement.appendChild(movieElement);
                });
            });
    }

    function generateStars(rating) {
        let stars = '';
        for(let i = 0; i < rating; i++) {
            stars += '<span class="fa fa-star checked"></span>';
        }
        for(let i = 0; i < (5 - rating); i++) {
            stars += '<span class="fa fa-star"></span>';
        }
        return stars;
    }

    function createMovie(e) {
        e.preventDefault();
        let title = document.getElementById('new-movie-title').value;
        let myReview = document.getElementById('new-movie-review').value;
        let star = document.getElementById('new-movie-star').value;
        let movie = { title, myReview, star };

        fetch('/api/movies', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(movie)
        })
            .then(() => window.location.href = 'index.html');
    }

    function updateMovie(e, id) {
        e.preventDefault();
        let title = document.getElementById('edit-movie-title').value;
        let myReview = document.getElementById('edit-movie-review').value;
        let star = document.getElementById('edit-movie-star').value;
        let movie = { title, myReview, star };

        fetch(`/api/movies/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(movie)
        })
            .then(() => window.location.href = 'index.html');
    }

    if (document.getElementById('new-movie-form')) {
        document.getElementById('new-movie-form').addEventListener('submit', createMovie);
    }

    if (document.getElementById('edit-movie-form')) {
        let movieId = location.search.split('=')[1];
        getMovie(movieId);
        document.getElementById('edit-movie-form').addEventListener('submit', (e) => updateMovie(e, movieId));
    }

    if (document.getElementById('movies')) fetchMovies();
});

function getMovie(id) {
    fetch(`/api/movies/${id}`)
        .then(response => response.json())
        .then(movie => fillForm(movie));
}

function fillForm(movie) {
    document.getElementById('edit-movie-title').value = movie.title;
    document.getElementById('edit-movie-review').value = movie.myReview;
    document.getElementById('edit-movie-star').value = movie.star;
}